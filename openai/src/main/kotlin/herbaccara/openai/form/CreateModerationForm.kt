package herbaccara.openai.form

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class CreateModerationForm @JvmOverloads constructor(
    val input: String,
    val model: String? = null
) : Form {

    override fun validate() {
        // nothing
    }
}
