package herbaccara.openai.form

import com.fasterxml.jackson.annotation.JsonInclude
import com.toasttab.ksp.builder.annotations.GenerateBuilder

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@GenerateBuilder
data class CreateModerationForm @JvmOverloads constructor(
    val input: String,
    val model: String? = null
) : Form {

    companion object {

        @JvmStatic
        fun builder(): CreateModerationFormBuilder = CreateModerationFormBuilder()
    }

    override fun validate() {
        // nothing
    }
}
