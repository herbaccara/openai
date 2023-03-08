package herbaccara.openai.model.moderation

import com.fasterxml.jackson.annotation.JsonProperty

data class Categories(
    val sexual: Boolean,
    val hate: Boolean,
    val violence: Boolean,
    @field:JsonProperty("self-harm")
    val selfHarm: Boolean,
    @field:JsonProperty("sexual/minors")
    val sexualMinors: Boolean,
    @field:JsonProperty("hate/threatening")
    val hateThreatening: Boolean,
    @field:JsonProperty("violence/graphic")
    val violenceGraphic: Boolean
)
