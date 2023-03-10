package herbaccara.openai.form

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import herbaccara.openai.form.validation.ValidationUtils

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class CreateCompletionForm @JvmOverloads constructor(
    val model: String,
    val prompt: String,
    val suffix: String? = null,
    @field:JsonProperty("max_tokens") val maxTokens: Int? = null,
    val temperature: Double? = null,
    @field:JsonProperty("top_p") val topP: Int? = null,
    val n: Int? = null,
    val stream: Boolean? = null,
    val logprobs: Int? = null,
    val echo: Boolean? = null,
    val stop: String? = null,
    @field:JsonProperty("presence_penalty") val presencePenalty: Double? = null,
    @field:JsonProperty("frequency_penalty") val frequencyPenalty: Double? = null,
    @field:JsonProperty("best_of") val bestOf: Int? = null,
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
