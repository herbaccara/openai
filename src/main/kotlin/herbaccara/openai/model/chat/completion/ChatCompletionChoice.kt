package herbaccara.openai.model.chat.completion

import com.fasterxml.jackson.annotation.JsonProperty
import herbaccara.openai.model.Message

data class ChatCompletionChoice(
    val message: Message,
    @field:JsonProperty("finish_reason")
    val finishReason: String,
    val index: Int
)
