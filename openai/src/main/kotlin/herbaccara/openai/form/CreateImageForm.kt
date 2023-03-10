package herbaccara.openai.form

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.toasttab.ksp.builder.annotations.GenerateBuilder
import herbaccara.openai.form.enums.ImageResponseFormat
import herbaccara.openai.form.validation.ValidationUtils

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@GenerateBuilder
data class CreateImageForm @JvmOverloads constructor(
    /***
     * A text description of the desired image(s). The maximum length is 1000 characters.
     */
    val prompt: String,
    val n: Int? = null,
    val size: String? = null,
    @field:JsonProperty("response_format") val responseFormat: ImageResponseFormat? = null,
    val user: String? = null
) : Form {

    companion object {

        @JvmStatic
        fun builder(): CreateImageFormBuilder = CreateImageFormBuilder()
    }

    override fun validate() {
        ValidationUtils.lessThan(::prompt, 1000)
        ValidationUtils.between(::n, 1.0, 10.0)
        ValidationUtils.imageSize(::size)
    }
}
