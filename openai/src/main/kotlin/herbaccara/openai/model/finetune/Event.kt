package herbaccara.openai.model.finetune

import com.fasterxml.jackson.annotation.JsonProperty

data class Event(
    @field:JsonProperty("object")
    val `object`: String,
    @field:JsonProperty("created_at")
    val createdAt: Long,
    val level: String,
    val message: String
)
