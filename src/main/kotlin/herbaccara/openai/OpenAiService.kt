package herbaccara.openai

import herbaccara.openai.form.*
import herbaccara.openai.model.audio.AudioResult
import herbaccara.openai.model.chat.completion.ChatCompletion
import herbaccara.openai.model.completion.Completion
import herbaccara.openai.model.edit.Edit
import herbaccara.openai.model.embedding.EmbeddingResult
import herbaccara.openai.model.file.DeleteFile
import herbaccara.openai.model.file.File
import herbaccara.openai.model.file.FileResult
import herbaccara.openai.model.image.ImageResult
import herbaccara.openai.model.model.Model
import herbaccara.openai.model.model.ModelResult
import herbaccara.openai.model.moderation.ModerationResult
import org.springframework.core.io.FileSystemResource
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange
import org.springframework.web.client.getForObject
import org.springframework.web.client.postForObject

class OpenAiService(
    private val restTemplate: RestTemplate
) {
    fun models(): ModelResult {
        val uri = "/v1/models"
        return restTemplate.getForObject(uri)
    }

    fun model(model: String): Model {
        val uri = "/v1/models/$model"
        return restTemplate.getForObject(uri)
    }

    fun completion(form: CompletionForm): Completion {
        val uri = "/v1/completions"
        return restTemplate.postForObject(uri, form)
    }

    fun chatCompletion(form: ChatCompletionForm): ChatCompletion {
        val uri = "/v1/chat/completions"
        return restTemplate.postForObject(uri, form)
    }

    fun edit(form: EditForm): Edit {
        val uri = "/v1/edits"
        return restTemplate.postForObject(uri, form)
    }

    fun imageGeneration(form: ImageGenerationForm): ImageResult {
        val uri = "/v1/images/generations"
        return restTemplate.postForObject(uri, form)
    }

    fun imageEdit(form: ImageEditForm): ImageResult {
        val uri = "/v1/images/edits"

        val headers = HttpHeaders().apply {
            contentType = MediaType.MULTIPART_FORM_DATA
        }

        val body = LinkedMultiValueMap<String, Any>().apply {
            add("image", FileSystemResource(form.image))
            if (form.mask != null) {
                add("mask", FileSystemResource(form.mask))
            }
            add("prompt", form.prompt)
            if (form.n != null) {
                add("n", form.n)
            }
            if (form.size != null) {
                add("size", form.size)
            }
            if (form.responseFormat != null) {
                add("response_format", form.responseFormat)
            }
            if (form.user != null) {
                add("user", form.user)
            }
        }

        val httpEntity = HttpEntity(body, headers)

        return restTemplate.postForObject(uri, httpEntity)
    }

    fun imageVariation(form: ImageVariationForm): ImageResult {
        val uri = "/v1/images/variations"

        val headers = HttpHeaders().apply {
            contentType = MediaType.MULTIPART_FORM_DATA
        }

        val body = LinkedMultiValueMap<String, Any>().apply {
            add("image", FileSystemResource(form.image))
            if (form.n != null) {
                add("n", form.n)
            }
            if (form.size != null) {
                add("size", form.size)
            }
            if (form.responseFormat != null) {
                add("response_format", form.responseFormat)
            }
            if (form.user != null) {
                add("user", form.user)
            }
        }

        val httpEntity = HttpEntity(body, headers)

        return restTemplate.postForObject(uri, httpEntity)
    }

    fun embedding(form: EmbeddingForm): EmbeddingResult {
        val uri = "/v1/embeddings"
        return restTemplate.postForObject(uri, form)
    }

    fun audioTranscription(form: AudioTranscriptionForm): AudioResult {
        val uri = "/v1/audio/transcriptions"

        val headers = HttpHeaders().apply {
            contentType = MediaType.MULTIPART_FORM_DATA
        }

        val body = LinkedMultiValueMap<String, Any>().apply {
            add("file", FileSystemResource(form.file))
            add("model", form.model)
            if (form.prompt != null) {
                add("prompt", form.prompt)
            }
            if (form.responseFormat != null) {
                add("response_format", form.responseFormat)
            }
            if (form.temperature != null) {
                add("temperature", form.temperature)
            }
            if (form.language != null) {
                add("language", form.language)
            }
        }

        val httpEntity = HttpEntity(body, headers)

        return restTemplate.postForObject(uri, httpEntity)
    }

    fun audioTranslation(form: AudioTranslationForm): AudioResult {
        val uri = "/v1/audio/translations"

        val headers = HttpHeaders().apply {
            contentType = MediaType.MULTIPART_FORM_DATA
        }

        val body = LinkedMultiValueMap<String, Any>().apply {
            add("file", FileSystemResource(form.file))
            add("model", form.model)
            if (form.prompt != null) {
                add("prompt", form.prompt)
            }
            if (form.responseFormat != null) {
                add("response_format", form.responseFormat)
            }
            if (form.temperature != null) {
                add("temperature", form.temperature)
            }
        }

        val httpEntity = HttpEntity(body, headers)

        return restTemplate.postForObject(uri, httpEntity)
    }

    fun files(): FileResult {
        val uri = "/v1/files"

        return restTemplate.getForObject(uri)
    }

    fun uploadFile(form: UploadFileForm): File {
        val uri = "/v1/files"

        val headers = HttpHeaders().apply {
            contentType = MediaType.MULTIPART_FORM_DATA
        }

        val body = LinkedMultiValueMap<String, Any>().apply {
            add("file", FileSystemResource(form.file))
            add("purpose", form.purpose)
        }

        val httpEntity = HttpEntity(body, headers)

        return restTemplate.postForObject(uri, httpEntity)
    }

    fun deleteFile(fileId: String): DeleteFile {
        val uri = "/v1/files/$fileId"

        return restTemplate.exchange<DeleteFile>(uri, HttpMethod.DELETE, HttpEntity.EMPTY).body!!
    }

    fun retrieveFile(fileId: String): File {
        val uri = "/v1/files/$fileId"

        return restTemplate.getForObject(uri)
    }

    fun retrieveFileContent(fileId: String): String {
        val uri = "/v1/files/$fileId/content"

        return restTemplate.getForObject(uri)
    }

    // Fine-tunes

    fun moderation(form: ModerationForm): ModerationResult {
        val uri = "/v1/moderations"

        return restTemplate.postForObject(uri, form)
    }
}
