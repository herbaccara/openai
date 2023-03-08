package herbaccara.openai.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Result<T>(
    @field:JsonProperty("object") val `object`: String,
    val data: List<T>
)
