package herbaccara.openai.form

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class ImageGenerationForm @JvmOverloads constructor(
    val prompt: String,
    val n: Int? = null,
    val size: String? = null,
    @field:JsonProperty("response_format")
    val responseFormat: ImageResponseFormat? = null,
    val user: String? = null
)
