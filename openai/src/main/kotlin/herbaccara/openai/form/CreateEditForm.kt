package herbaccara.openai.form

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import herbaccara.openai.form.validation.ValidationUtils

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class CreateEditForm @JvmOverloads constructor(
    /***
     * ID of the model to use. You can use the text-davinci-edit-001 or code-davinci-edit-001 model with this endpoint.
     */
    val model: String,
    val input: String? = null,
    val instruction: String,
    val n: Int? = null,
    val temperature: Double? = null,
    @field:JsonProperty("top_p")
    val topP: Int? = null
) {
    init {
        ValidationUtils.between(::temperature, 0.0, 2.0)
    }
}