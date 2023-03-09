package herbaccara.openai.form

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import herbaccara.openai.form.validation.ValidationUtils

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class CreateImageForm @JvmOverloads constructor(
    /***
     * A text description of the desired image(s). The maximum length is 1000 characters.
     */
    val prompt: String,
    val n: Int? = null,
    val size: String? = null,
    @field:JsonProperty("response_format")
    val responseFormat: ImageResponseFormat? = null,
    val user: String? = null
) {
    init {
        ValidationUtils.lessThan(::prompt, 1000)
        ValidationUtils.between(::n, 1.0, 10.0)
        ValidationUtils.imageSize(::size)
    }
}
