package herbaccara.openai.model.chat.completion

import com.fasterxml.jackson.annotation.JsonProperty
import herbaccara.openai.model.Usage

data class ChatCompletion(
    val id: String,
    @field:JsonProperty("object")
    val `object`: String,
    val created: Int,
    val model: String,
    val choices: List<Choice>,
    val usage: Usage
)
