package herbaccara.openai.model.completion

import com.fasterxml.jackson.annotation.JsonProperty

data class TextCompletion(
    val id: String,
    @field:JsonProperty("object")
    val `object`: String,
    val created: Int,
    val choices: List<CompletionChoice>,
    val model: String
)
