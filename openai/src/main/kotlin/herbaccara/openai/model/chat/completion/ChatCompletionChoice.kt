package herbaccara.openai.model.chat.completion

import com.fasterxml.jackson.annotation.JsonProperty

data class ChatCompletionChoice(
    val message: Message,
    @field:JsonProperty("finish_reason")
    val finishReason: String?,
    val index: Int
)
