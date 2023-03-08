package herbaccara.openai.form

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class EditForm @JvmOverloads constructor(
    val model: String,
    val input: String? = null,
    val instruction: String,
    val n: Int? = null,
    @field:JsonProperty("top_p")
    val temperature: Int? = null,
    val topP: Int? = null
)
