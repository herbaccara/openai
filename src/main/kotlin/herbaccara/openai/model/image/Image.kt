package herbaccara.openai.model.image

import com.fasterxml.jackson.annotation.JsonProperty

data class Image(
    val url: String? = null,
    @field:JsonProperty("b64_json")
    val b64Json: String? = null
)
