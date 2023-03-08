package herbaccara.openai.model.moderation

import com.fasterxml.jackson.annotation.JsonProperty

data class Moderation(
    val flagged: Boolean,
    val categories: Categories,
    @field:JsonProperty("category_scores")
    val categoryScores: CategoryScores
)
