package herbaccara.openai.model.chat.completion.chunk

import com.fasterxml.jackson.annotation.JsonProperty

data class ChatCompletionChunk(
    val id: String,
    @field:JsonProperty("object")
    val `object`: String,
    val created: Int,
    val model: String,
    val choices: List<ChatCompletionChunkChoice>
)
