package herbaccara.openai

import herbaccara.openai.form.ChatCompletionForm
import herbaccara.openai.form.CompletionForm
import herbaccara.openai.form.EditForm
import herbaccara.openai.model.chat.completion.ChatCompletion
import herbaccara.openai.model.completion.Completion
import herbaccara.openai.model.edit.Edit
import herbaccara.openai.model.model.Model
import herbaccara.openai.model.model.ModelResult
import org.springframework.web.client.RestTemplate
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
}
