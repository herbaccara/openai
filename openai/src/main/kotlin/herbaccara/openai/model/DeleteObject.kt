package herbaccara.openai.model

import com.fasterxml.jackson.annotation.JsonProperty

data class DeleteObject(
    val id: String,
    @field:JsonProperty("object")
    val `object`: String,
    val deleted: Boolean
)
