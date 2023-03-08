package herbaccara.openai

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import herbaccara.openai.form.*
import herbaccara.openai.model.DeleteObject
import herbaccara.openai.model.chat.completion.ChatCompletion
import herbaccara.openai.model.completion.Completion
import herbaccara.openai.model.edit.Edit
import herbaccara.openai.model.embedding.EmbeddingResult
import herbaccara.openai.model.file.File
import herbaccara.openai.model.finetune.Event
import herbaccara.openai.model.finetune.FineTune
import herbaccara.openai.model.image.ImageResult
import herbaccara.openai.model.model.Model
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
    private val restTemplate: RestTemplate,
    private val objectMapper: ObjectMapper
) {
    fun listModels(): List<Model> {
        val uri = "/v1/models"

        val json = restTemplate.getForObject<JsonNode>(uri)

        return objectMapper.readValue(json["data"].toString(), object : TypeReference<List<Model>>() {})
    }

    fun retrieveModel(model: String): Model {
        val uri = "/v1/models/$model"
        return restTemplate.getForObject(uri)
    }

    fun createCompletion(form: CreateCompletionForm): Completion {
        val uri = "/v1/completions"
        return restTemplate.postForObject(uri, form)
    }

    fun createChatCompletion(form: CreateChatCompletionForm): ChatCompletion {
        val uri = "/v1/chat/completions"
        return restTemplate.postForObject(uri, form)
    }

    fun createEdit(form: CreateEditForm): Edit {
        val uri = "/v1/edits"
        return restTemplate.postForObject(uri, form)
    }

    fun createImage(form: CreateImageForm): ImageResult {
        val uri = "/v1/images/generations"
        return restTemplate.postForObject(uri, form)
    }

    fun createImageEdit(form: CreateImageEditForm): ImageResult {
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

    fun createImageVariation(form: CreateImageVariationForm): ImageResult {
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

    fun createEmbedding(form: CreateEmbeddingForm): EmbeddingResult {
        val uri = "/v1/embeddings"
        return restTemplate.postForObject(uri, form)
    }

    fun createTranscription(form: CreateTranscriptionForm): String {
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

        val json = restTemplate.postForObject<JsonNode>(uri, httpEntity)
        return json["text"].asText()
    }

    fun createTranslation(form: CreateTranslationForm): String {
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

        val json = restTemplate.postForObject<JsonNode>(uri, httpEntity)
        return json["text"].asText()
    }

    fun listFiles(): List<File> {
        val uri = "/v1/files"

        val json = restTemplate.getForObject<JsonNode>(uri)

        return objectMapper.readValue(json["data"].toString(), object : TypeReference<List<File>>() {})
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

    fun deleteFile(fileId: String): DeleteObject {
        val uri = "/v1/files/$fileId"

        return restTemplate.exchange<DeleteObject>(uri, HttpMethod.DELETE, HttpEntity.EMPTY).body!!
    }

    fun retrieveFile(fileId: String): File {
        val uri = "/v1/files/$fileId"

        return restTemplate.getForObject(uri)
    }

    fun retrieveFileContent(fileId: String): String {
        val uri = "/v1/files/$fileId/content"

        return restTemplate.getForObject(uri)
    }

    fun createFineTune(form: CreateFineTuneForm): FineTune {
        val uri = "/v1/fine-tunes"

        return restTemplate.postForObject(uri, form)
    }

    fun listFineTunes(): List<FineTune> {
        val uri = "/v1/fine-tunes"

        val json = restTemplate.getForObject<JsonNode>(uri)

        return objectMapper.readValue(json["data"].toString(), object : TypeReference<List<FineTune>>() {})
    }

    fun retrieveFineTune(fineTuneId: String): FineTune {
        val uri = "/v1/fine-tunes/$fineTuneId"

        return restTemplate.getForObject(uri)
    }

    fun cancelFineTune(fineTuneId: String): FineTune {
        val uri = "/v1/fine-tunes/$fineTuneId/cancel"

        return restTemplate.postForObject(uri)
    }

    fun listFineTuneEvents(fineTuneId: String): List<Event> {
        val uri = "/v1/fine-tunes/$fineTuneId/events"

        val json = restTemplate.getForObject<JsonNode>(uri)

        return objectMapper.readValue(json["data"].toString(), object : TypeReference<List<Event>>() {})
    }

    fun deleteFineTuneModel(model: String): DeleteObject {
        val uri = "/v1/models/$model"

        return restTemplate.exchange<DeleteObject>(uri, HttpMethod.DELETE, HttpEntity.EMPTY).body!!
    }

    fun createModeration(form: CreateModerationForm): ModerationResult {
        val uri = "/v1/moderations"

        return restTemplate.postForObject(uri, form)
    }
}
