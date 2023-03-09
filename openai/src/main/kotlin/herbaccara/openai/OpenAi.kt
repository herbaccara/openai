package herbaccara.openai

import herbaccara.openai.form.*
import herbaccara.openai.model.DeleteObject
import herbaccara.openai.model.audio.AudioResult
import herbaccara.openai.model.chat.completion.ChatCompletion
import herbaccara.openai.model.completion.Completion
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
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface OpenAi {

    @GET("/v1/models")
    fun listModels(): Call<ListModels>

    @GET("/v1/models/{model}")
    fun retrieveModel(@Path("model") model: String): Call<Model>

    @POST("/v1/completions")
    fun createCompletion(@Body form: CreateCompletionForm): Call<Completion>

    @POST("/v1/chat/completions")
    fun createChatCompletion(@Body form: CreateChatCompletionForm): Call<ChatCompletion>

    @POST("/v1/edits")
    fun createEdit(@Body form: CreateEditForm): Call<Edit>

    @POST("/v1/images/generations")
    fun createImage(@Body form: CreateImageForm): Call<ImageResult>

    @Multipart
    @POST("/v1/images/edits")
    fun createImageEdit(
        @Part image: MultipartBody.Part,
        @Part mask: MultipartBody.Part? = null,
        @Part("prompt") prompt: RequestBody,
        @Part("n") n: RequestBody? = null,
        @Part("size") size: RequestBody? = null,
        @Part("response_format") responseFormat: RequestBody? = null,
        @Part("user") user: RequestBody? = null
    ): Call<ImageResult>

    @Multipart
    @POST("/v1/images/variations")
    fun createImageVariation(
        @Part image: MultipartBody.Part,
        @Part("n") n: RequestBody? = null,
        @Part("size") size: RequestBody? = null,
        @Part("response_format") responseFormat: RequestBody? = null,
        @Part("user") user: RequestBody? = null
    ): Call<ImageResult>

    @POST("/v1/embeddings")
    fun createEmbedding(@Body form: CreateEmbeddingForm): Call<EmbeddingResult>

    @Multipart
    @POST("/v1/audio/transcriptions")
    fun createTranscription(
        @Part file: MultipartBody.Part,
        @Part("model") model: RequestBody,
        @Part("prompt") prompt: RequestBody? = null,
        @Part("response_format") responseFormat: RequestBody? = null,
        @Part("temperature") temperature: RequestBody? = null,
        @Part("language") language: RequestBody? = null
    ): Call<AudioResult>

    @Multipart
    @POST("/v1/audio/translations")
    fun createTranslation(
        @Part file: MultipartBody.Part,
        @Part("model") model: RequestBody,
        @Part("prompt") prompt: RequestBody? = null,
        @Part("response_format") responseFormat: RequestBody? = null,
        @Part("temperature") temperature: RequestBody? = null
    ): Call<AudioResult>

    @GET("/v1/files")
    fun listFiles(): Call<ListFiles>

    @Multipart
    @POST("/v1/files")
    fun uploadFile(
        @Part file: MultipartBody.Part,
        @Part("purpose") purpose: RequestBody
    ): Call<File>

    @DELETE("/v1/files/{file_id}")
    fun deleteFile(@Path("file_id") fileId: String): Call<DeleteObject>

    @GET("/v1/files/{file_id}")
    fun retrieveFile(@Path("file_id") fileId: String): Call<File>

    @GET("/v1/files/{file_id}/content")
    fun retrieveFileContent(@Path("file_id") fileId: String): Call<String>

    @POST("/v1/fine-tunes")
    fun createFineTune(@Body form: CreateFineTuneForm): Call<FineTune>

    @GET("/v1/fine-tunes")
    fun listFineTunes(): Call<ListFineTunes>

    @GET("/v1/fine-tunes/{fine_tune_id}")
    fun retrieveFineTune(@Path("fine_tune_id") fineTuneId: String): Call<FineTune>

    @POST("/v1/fine-tunes/{fine_tune_id}/cancel")
    fun cancelFineTune(@Path("fine_tune_id") fineTuneId: String): Call<FineTune>

    @GET("/v1/fine-tunes/{fine_tune_id}/events")
    fun listFineTuneEvents(
        @Path("fine_tune_id") fineTuneId: String,
        @Query("stream") stream: Boolean
    ): Call<ListFineTuneEvents>

    @DELETE("/v1/models/{model}")
    fun deleteFineTuneModel(@Path("model") model: String): Call<DeleteObject>

    @POST("/v1/moderations")
    fun createModeration(@Body form: CreateModerationForm): Call<ModerationResult>
}
