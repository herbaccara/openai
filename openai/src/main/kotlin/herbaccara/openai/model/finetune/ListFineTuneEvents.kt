package herbaccara.openai.model.finetune

import com.fasterxml.jackson.annotation.JsonProperty

data class ListFineTuneEvents(
    @field:JsonProperty("object")
    val `object`: String,
    val data: List<Event>
)