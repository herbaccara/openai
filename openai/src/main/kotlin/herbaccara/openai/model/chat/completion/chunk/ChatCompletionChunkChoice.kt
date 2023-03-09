package herbaccara.openai.model.chat.completion.chunk

import com.fasterxml.jackson.annotation.JsonProperty

data class ChatCompletionChunkChoice(
    val delta: ChatCompletionChunkChoiceDelta,
    val index: Int,
    @field:JsonProperty("finish_reason")
    val finishReason: String? = null
)
