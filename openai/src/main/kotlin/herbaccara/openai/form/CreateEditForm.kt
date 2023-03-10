package herbaccara.openai.form

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.toasttab.ksp.builder.annotations.GenerateBuilder
import herbaccara.openai.form.validation.ValidationUtils

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@GenerateBuilder
data class CreateEditForm @JvmOverloads constructor(
    /***
     * ID of the model to use. You can use the text-davinci-edit-001 or code-davinci-edit-001 model with this endpoint.
     */
    val model: String,
    val input: String? = null,
    val instruction: String,
    val n: Int? = null,
    val temperature: Double? = null,
    @field:JsonProperty("top_p") val topP: Int? = null
) : Form {

    companion object {

        @JvmStatic
        fun builder(): CreateEditFormBuilder = CreateEditFormBuilder()
    }

    override fun validate() {
        ValidationUtils.between(::temperature, 0.0, 2.0)
    }
}
