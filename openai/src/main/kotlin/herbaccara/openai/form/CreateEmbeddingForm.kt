package herbaccara.openai.form

import com.fasterxml.jackson.annotation.JsonInclude
import com.toasttab.ksp.builder.annotations.GenerateBuilder

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@GenerateBuilder
data class CreateEmbeddingForm @JvmOverloads constructor(
    val model: String,
    val input: String,
    val user: String? = null
) : Form {

    companion object {

        @JvmStatic
        fun builder(): CreateEmbeddingFormBuilder = CreateEmbeddingFormBuilder()
    }

    override fun validate() {
        // nothing
    }
}
