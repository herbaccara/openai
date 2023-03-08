package herbaccara.openai.model.embedding

import com.fasterxml.jackson.annotation.JsonProperty

data class EmbeddingResult(
    @field:JsonProperty("object")
    var `object`: String,
    var data: ArrayList<Embedding>,
    var model: String,
    var usage: Usage
)
