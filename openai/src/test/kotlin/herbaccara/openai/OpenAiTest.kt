package herbaccara.openai

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import herbaccara.openai.form.*
import herbaccara.openai.model.chat.completion.Message
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody.Part.Companion.createFormData
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.io.File
import java.time.Duration

class OpenAiTest {

    val apiKey = "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"

    val client = OkHttpClient.Builder()
        .addInterceptor {
            val request = it.request().newBuilder()
                .addHeader("Authorization", "Bearer $apiKey")
                .build()
            it.proceed(request)
        }
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .readTimeout(Duration.ofSeconds(30))
        .build()

    val objectMapper = jacksonObjectMapper().apply {
        findAndRegisterModules()
    }

    val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://api.openai.com")
        .addConverterFactory(JacksonConverterFactory.create(objectMapper))
        .build()

    val openAiService = retrofit.create(OpenAi::class.java)

    @Test
    fun listModels() {
        val listModels = openAiService.listModels().execute().body()
        println(listModels)
    }

    @Test
    fun retrieveModel() {
        val model = openAiService.retrieveModel("text-davinci-003").execute().body()
        println()
    }

    @Test
    fun completion() {
        val form = CreateCompletionForm("text-davinci-003", "Say this is a test", maxTokens = 7, temperature = 0)
        val completion = openAiService.createCompletion(form).execute().body()
        println(completion)
    }

    @Test
    fun chatCompletion() {
        val form = CreateChatCompletionForm("gpt-3.5-turbo", listOf(Message("user", "hello")))
        val chatCompletion = openAiService.createChatCompletion(form).execute().body()
        println()
    }

    @Test
    fun edit() {
        val form = CreateEditForm("text-davinci-edit-001", "What day of the wek is it?", "Fix the spelling mistakes")
        val edit = openAiService.createEdit(form).execute().body()
        println()
    }

    @Test
    fun imageGenerationUrl() {
        val form = CreateImageForm("A cute baby sea otter", n = 2, size = "1024x1024", responseFormat = ImageResponseFormat.url)
        val result = openAiService.createImage(form).execute().body()
        println()
    }

    @Test
    fun imageEdit() {
        val form = CreateImageEditForm(
            image = File("src/test/resources/penguin.png"),
            prompt = "A cute baby sea otter wearing a beret",
            n = 2,
            size = "1024x1024"
        )
        val (image, mask, prompt, n, size, responseFormat, user) = form

        val imagePart = createFormData(
            CreateImageEditForm::image.name, image.name,
            image.asRequestBody("image/*".toMediaTypeOrNull())
        )

//        val imagePart = image.asRequestBody("image/png".toMediaTypeOrNull())
        val promptPart = prompt.toRequestBody("multipart/form-data".toMediaType())
        val nPart = n!!.toString().toRequestBody("multipart/form-data".toMediaType())

        val result = openAiService.createImageEdit(imagePart, prompt = promptPart, n = nPart).execute().body()
        println()
    }
}

