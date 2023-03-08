package herbaccara.openai.model.file

import com.fasterxml.jackson.annotation.JsonProperty

data class DeleteFile(
    val id: String,
    @field:JsonProperty("object")
    val `object`: String,
    val deleted: Boolean
)
