package herbaccara.openai.model.embedding

import com.fasterxml.jackson.annotation.JsonProperty

data class EmbeddingResult(
    @field:JsonProperty("object")
    val `object`: String,
    val data: List<Embedding>,
    val model: String,
    val usage: EmbeddingUsage
)
