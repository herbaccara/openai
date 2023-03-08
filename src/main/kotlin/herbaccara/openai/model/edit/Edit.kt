package herbaccara.openai.model.edit

import com.fasterxml.jackson.annotation.JsonProperty
import herbaccara.openai.model.Usage

data class Edit(
    @field:JsonProperty("object")
    val `object`: String,
    val created: Int,
    val choices: List<Choice>,
    val usage: Usage
)
