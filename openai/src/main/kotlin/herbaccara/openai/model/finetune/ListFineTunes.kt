package herbaccara.openai.model.finetune

import com.fasterxml.jackson.annotation.JsonProperty

data class ListFineTunes(
    @field:JsonProperty("object")
    val `object`: String,
    val data: List<FineTune>
)