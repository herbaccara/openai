package herbaccara.openai.model.completion

import com.fasterxml.jackson.annotation.JsonProperty

data class CompletionChoice(
    val text: String,
    val index: Int,
    val logprobs: Logprobs?,
    @field:JsonProperty("finish_reason") val finishReason: String?
)
