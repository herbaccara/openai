package herbaccara.openai.form

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class EmbeddingForm @JvmOverloads constructor(
    val model: String,
    val input: String,
    val user: String? = null
)