package herbaccara.openai

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import herbaccara.openai.exception.OpenAiException
import herbaccara.openai.form.*
import herbaccara.openai.log.Logging
import herbaccara.openai.model.DeleteObject
import herbaccara.openai.model.Error
import herbaccara.openai.model.audio.AudioResult
import herbaccara.openai.model.chat.completion.ChatCompletion
import herbaccara.openai.model.chat.completion.chunk.ChatCompletionChunk
import herbaccara.openai.model.completion.Completion
import herbaccara.openai.model.completion.TextCompletion
import herbaccara.openai.model.edit.Edit
import herbaccara.openai.model.embedding.EmbeddingResult
import herbaccara.openai.model.file.File
import herbaccara.openai.model.file.ListFiles
import herbaccara.openai.model.finetune.FineTune
import herbaccara.openai.model.finetune.ListFineTuneEvents
import herbaccara.openai.model.finetune.ListFineTunes
import herbaccara.openai.model.image.ImageResult
import herbaccara.openai.model.model.ListModels
import herbaccara.openai.model.model.Model
import herbaccara.openai.model.moderation.ModerationResult
import herbaccara.openai.sse.CompletionEventSourceListener
import herbaccara.openai.sse.Event
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody.Part.Companion.createFormData
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import okhttp3.sse.EventSource
import okhttp3.sse.EventSources
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.net.Proxy
import java.time.Duration
import java.util.function.Consumer

class OpenAiService {

    companion object {
        const val BASE_URL: String = "https://api.openai.com"
        val DEFAULT_TIMEOUT: Duration = Duration.ofSeconds(30)
    }

    @JvmOverloads
    constructor(
        apiKey: String,
        baseUrl: String = BASE_URL,
        timeout: Duration = DEFAULT_TIMEOUT,
        validate: Boolean = false,
        logging: Logging = Logging(),
        proxy: Proxy? = null
    ) {
        client = OkHttpClient.Builder()
            .proxy(proxy)
            .addInterceptor(AuthorizationInterceptor(apiKey))
            .apply {
                if (logging.enable) {
                    addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = Level.valueOf(logging.level.name)
                        }
                    )
                }
            }
            .readTimeout(timeout)
            .build()

        openAi = Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .build()
            .create(OpenAi::class.java)

        this.validate = validate
    }

    protected val objectMapper: ObjectMapper = jacksonObjectMapper().apply {
        findAndRegisterModules()
    }

    protected val client: OkHttpClient
    protected val openAi: OpenAi
    private val validate: Boolean

    private fun <T> execute(block: () -> Call<T>): T {
        val call = block()
        val response = call.execute()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val errorStr = response.errorBody()?.string()
            val error: Error? = errorStr?.let { objectMapper.readValue(errorStr) }
            throw OpenAiException.HttpErrorException("OpenAi error", error = error)
        }
    }

    fun listModels(): ListModels {
        return execute(openAi::listModels)
    }

    fun retrieveModel(model: String): Model {
        return execute { openAi.retrieveModel(model) }
    }

    fun createCompletion(form: CreateCompletionForm): Completion {
        val copy = form.copy(stream = false)
        if (validate) copy.validate()
        return execute { openAi.createCompletion(copy) }
    }

    fun createCompletions(form: CreateCompletionForm, block: (Event<TextCompletion>) -> Unit): EventSource {
        val copy = form.copy(stream = true)
        if (validate) copy.validate()

        val json = objectMapper.writeValueAsString(copy)

        val request = Request.Builder()
            .url("$BASE_URL/v1/completions")
            .post(json.toRequestBody("application/json")!!)
            .build()

        val factory = EventSources.createFactory(client)

        return factory.newEventSource(
            request,
            CompletionEventSourceListener(objectMapper, TextCompletion::class.java, block)
        )
    }

    fun createChatCompletion(form: CreateChatCompletionForm): ChatCompletion {
        val copy = form.copy(stream = false)
        if (validate) copy.validate()
        return execute { openAi.createChatCompletion(copy) }
    }

    fun createChatCompletions(
        form: CreateChatCompletionForm,
        block: Consumer<Event<ChatCompletionChunk>>
    ): EventSource {
        val copy = form.copy(stream = true)
        if (validate) copy.validate()

        val json = objectMapper.writeValueAsString(copy)

        val request = Request.Builder()
            .url("$BASE_URL/v1/chat/completions")
            .post(json.toRequestBody("application/json")!!)
            .build()

        val factory = EventSources.createFactory(client)

        return factory.newEventSource(
            request,
            CompletionEventSourceListener(objectMapper, ChatCompletionChunk::class.java, block)
        )
    }

    fun createEdit(form: CreateEditForm): Edit {
        if (validate) form.validate()
        return execute { openAi.createEdit(form) }
    }

    fun createImage(form: CreateImageForm): ImageResult {
        if (validate) form.validate()
        return execute { openAi.createImage(form) }
    }

    fun createImageEdit(form: CreateImageEditForm): ImageResult {
        if (validate) form.validate()
        val (image, mask, prompt, n, size, responseFormat, user) = form

        val imagePart = createFormData("image", image.name, image.asRequestBody("image/png".toMediaTypeOrNull()))
        val maskPart = mask?.let { createFormData("mask", it.name, it.asRequestBody("image/png".toMediaTypeOrNull())) }
        val promptPart = prompt.toRequestBody()!!
        val nPart = n.toRequestBody()
        val sizePart = size.toRequestBody()
        val responseFormatPart = responseFormat.toRequestBody()
        val userPart = user?.toRequestBody()

        return execute {
            openAi.createImageEdit(imagePart, maskPart, promptPart, nPart, sizePart, responseFormatPart, userPart)
        }
    }

    fun createImageVariation(form: CreateImageVariationForm): ImageResult {
        if (validate) form.validate()
        val (image, n, size, responseFormat, user) = form

        val imagePart = createFormData("image", image.name, image.asRequestBody("image/png".toMediaTypeOrNull()))
        val nPart = n.toRequestBody()
        val sizePart = size.toRequestBody()
        val responseFormatPart = responseFormat.toRequestBody()
        val userPart = user?.toRequestBody()

        return execute {
            openAi.createImageVariation(imagePart, nPart, sizePart, responseFormatPart, userPart)
        }
    }

    fun createEmbedding(form: CreateEmbeddingForm): EmbeddingResult {
        if (validate) form.validate()
        return execute { openAi.createEmbedding(form) }
    }

    @JvmOverloads
    fun createEmbedding(model: String, input: String, user: String? = null): EmbeddingResult {
        return createEmbedding(CreateEmbeddingForm(model, input, user))
    }

    fun createTranscription(form: CreateTranscriptionForm): AudioResult {
        if (validate) form.validate()
        val (file, model, prompt, responseFormat, temperature, language) = form

        val filePart = createFormData("file", file.name, file.asRequestBody("audio/*".toMediaTypeOrNull()))
        val modelPart = model.toRequestBody()!!
        val promptPart = prompt.toRequestBody()
        val responseFormatPart = responseFormat.toRequestBody()
        val temperaturePart = temperature?.toRequestBody()
        val languagePart = language?.toRequestBody()

        return execute {
            openAi.createTranscription(
                filePart,
                modelPart,
                promptPart,
                responseFormatPart,
                temperaturePart,
                languagePart
            )
        }
    }

    fun createTranslation(form: CreateTranslationForm): AudioResult {
        if (validate) form.validate()
        val (file, model, prompt, responseFormat, temperature) = form

        val filePart = createFormData("file", file.name, file.asRequestBody("audio/*".toMediaTypeOrNull()))
        val modelPart = model.toRequestBody()!!
        val promptPart = prompt.toRequestBody()
        val responseFormatPart = responseFormat.toRequestBody()
        val temperaturePart = temperature?.toRequestBody()

        return execute {
            openAi.createTranslation(filePart, modelPart, promptPart, responseFormatPart, temperaturePart)
        }
    }

    fun listFiles(): ListFiles {
        return execute(openAi::listFiles)
    }

    fun uploadFile(form: UploadFileForm): File {
        if (validate) form.validate()
        val (file, purpose) = form

        val filePart = createFormData("file", file.name, file.asRequestBody("text".toMediaTypeOrNull()))
        val purposePart = purpose.toRequestBody()!!

        return execute { openAi.uploadFile(filePart, purposePart) }
    }

    fun uploadFile(file: java.io.File, purpose: String): File {
        return uploadFile(UploadFileForm(file, purpose))
    }

    fun deleteFile(fileId: String): DeleteObject {
        return execute { openAi.deleteFile(fileId) }
    }

    fun retrieveFile(fileId: String): File {
        return execute { openAi.retrieveFile(fileId) }
    }

    fun retrieveFileContent(fileId: String): String {
        return execute { openAi.retrieveFileContent(fileId) }
    }

    fun createFineTune(form: CreateFineTuneForm): FineTune {
        if (validate) form.validate()
        return execute { openAi.createFineTune(form) }
    }

    fun listFineTunes(): ListFineTunes {
        return execute(openAi::listFineTunes)
    }

    fun retrieveFineTune(fineTuneId: String): FineTune {
        return execute { openAi.retrieveFineTune(fineTuneId) }
    }

    fun cancelFineTune(fineTuneId: String): FineTune {
        return execute { openAi.cancelFineTune(fineTuneId) }
    }

    @JvmOverloads
    fun listFineTuneEvents(fineTuneId: String, stream: Boolean = false): ListFineTuneEvents {
        return execute { openAi.listFineTuneEvents(fineTuneId, stream) }
    }

    fun deleteFineTuneModel(model: String): DeleteObject {
        return execute { openAi.deleteFineTuneModel(model) }
    }

    fun createModeration(form: CreateModerationForm): ModerationResult {
        if (validate) form.validate()
        return execute { openAi.createModeration(form) }
    }

    @JvmOverloads
    fun createModeration(input: String, model: String? = null): ModerationResult {
        return createModeration(CreateModerationForm(input, model))
    }

    // extension

    private fun Any?.toRequestBody(contentType: String = "multipart/form-data"): RequestBody? {
        return this?.toString()?.toRequestBody(contentType.toMediaType())
    }

    private fun Enum<*>?.toRequestBody(contentType: String = "multipart/form-data"): RequestBody? {
        return this?.name?.toRequestBody(contentType.toMediaType())
    }
}
