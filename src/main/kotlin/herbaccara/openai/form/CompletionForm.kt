package herbaccara.openai.form

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class CompletionForm @JvmOverloads constructor(
    val model: String,
    val prompt: String,
    val suffix: String? = null,
    @field:JsonProperty("max_tokens")
    val maxTokens: Int? = null,
    val temperature: Int? = null,
    @field:JsonProperty("top_p")
    val topP: Int? = null,
    val n: Int? = null,
    val stream: Boolean? = null,
    val logprobs: Int? = null,
    val echo: Boolean? = null,
    val stop: String? = null,
    @field:JsonProperty("presence_penalty")
    val presencePenalty: Double? = null,
    @field:JsonProperty("frequency_penalty")
    val frequencyPenalty: Double? = null,
    @field:JsonProperty("best_of")
    val bestOf: Int? = null,
    @field:JsonProperty("logit_bias")
    val logitBias: Map<String, Any> = emptyMap(),
    val user: String? = null
)
