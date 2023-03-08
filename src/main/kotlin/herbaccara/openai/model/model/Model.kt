package herbaccara.openai.model.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Model(
    val id: String,
    @field:JsonProperty("object")
    val `object`: String,
    val created: Int,
    @field:JsonProperty("owned_by")
    val ownedBy: String,
    val permission: List<Permission>,
    val root: String,
    val parent: String?
)
