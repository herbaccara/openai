package herbaccara.openai.model.completion

import com.fasterxml.jackson.annotation.JsonProperty

data class Logprobs(
    @field:JsonProperty("text_offset") val textOffset: List<Int>,
    @field:JsonProperty("token_logprobs") val tokenLogprobs: List<Double>,
    val tokens: List<String>,
    @field:JsonProperty("top_logprobs") val topLogprobs: List<Map<String, Double>>
)
