package herbaccara.openai.model.embedding

import com.fasterxml.jackson.annotation.JsonProperty

data class Embedding(
    @field:JsonProperty("object")
    val `object`: String,
    val index: Int,
    val embedding: List<Double>
)
