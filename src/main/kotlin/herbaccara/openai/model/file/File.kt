package herbaccara.openai.model.file

import com.fasterxml.jackson.annotation.JsonProperty

data class File(
    val id: String,
    @field:JsonProperty("object")
    val `object`: String,
    val bytes: Long,
    @field:JsonProperty("created_at")
    val createdAt: Long,
    val filename: String,
    val purpose: String
)
