package herbaccara.openai.form

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class ModerationForm @JvmOverloads constructor(
    val input: String,
    val model: String? = null
)
