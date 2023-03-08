package herbaccara.openai.model.moderation

import com.fasterxml.jackson.annotation.JsonProperty

data class CategoryScores(
    val sexual: Double,
    val hate: Double,
    val violence: Double,
    @field:JsonProperty("self-harm")
    val selfHarm: Double,
    @field:JsonProperty("sexual/minors")
    val sexualMinors: Double,
    @field:JsonProperty("hate/threatening")
    val hateThreatening: Double,
    @field:JsonProperty("violence/graphic")
    val violenceGraphic: Double
)
