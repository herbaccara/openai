package herbaccara.openai.form

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import herbaccara.openai.model.Message

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class ChatCompletionForm @JvmOverloads constructor(
    val model: String,
    val messages: List<Message>,
    val temperature: Int? = null,
    @field:JsonProperty("top_p")
    val topP: Int? = null,
    val n: Int? = null,
    val stream: Boolean? = null,
    val stop: String? = null,
    @field:JsonProperty("max_tokens")
    val maxTokens: Int? = null,
    @field:JsonProperty("presence_penalty")
    val presencePenalty: Double? = null,
    @field:JsonProperty("frequency_penalty")
    val frequencyPenalty: Double? = null,
    @field:JsonProperty("logit_bias")
    val logitBias: Map<String, Any> = emptyMap(),
    val user: String? = null
)
