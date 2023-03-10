package herbaccara.openai.form

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import herbaccara.openai.form.validation.ValidationUtils
import herbaccara.openai.model.chat.completion.Message

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class CreateChatCompletionForm @JvmOverloads constructor(
    /***
     * ID of the model to use. Currently, only gpt-3.5-turbo and gpt-3.5-turbo-0301 are supported.
     */
    val model: String,
    val messages: List<Message>,
    val temperature: Double? = null,
    @field:JsonProperty("top_p") val topP: Int? = null,
    val n: Int? = null,
    val stream: Boolean? = null,
    val stop: String? = null,
    @field:JsonProperty("max_tokens") val maxTokens: Int? = null,
    @field:JsonProperty("presence_penalty") val presencePenalty: Double? = null,
    @field:JsonProperty("frequency_penalty") val frequencyPenalty: Double? = null,
    @field:JsonProperty("logit_bias") val logitBias: Map<String, Int>?,
    val user: String? = null
) : Form {

    override fun validate() {
        ValidationUtils.between(::temperature, 0.0, 2.0)
        ValidationUtils.between(::presencePenalty, -2.0, 2.0)
        ValidationUtils.between(::frequencyPenalty, -2.0, 2.0)
        if (logitBias != null) {
            for ((k, v) in logitBias) {
                ValidationUtils.between("logitBias.$k", v, -100.0, 100.0)
            }
        }
    }
}
