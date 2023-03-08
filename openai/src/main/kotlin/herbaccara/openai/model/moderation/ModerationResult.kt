package herbaccara.openai.model.moderation

data class ModerationResult(
    val id: String,
    val model: String,
    val results: List<Moderation>
)
