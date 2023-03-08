package herbaccara.openai.model.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ModelResult(
    @field:JsonProperty("object") val `object`: String,
    val data: List<Model>
)
