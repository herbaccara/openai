package herbaccara.openai.model.completion

import com.fasterxml.jackson.annotation.JsonProperty

data class Choice(
    val text: String,
    val index: Int,
    val logprobs: Map<String, Any>?,
    @field:JsonProperty("finish_reason")
    val finishReason: String
)
