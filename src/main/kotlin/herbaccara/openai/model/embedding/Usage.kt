package herbaccara.openai.model.embedding

import com.fasterxml.jackson.annotation.JsonProperty

data class Usage(
    @field:JsonProperty("prompt_tokens")
    val promptTokens: Int,
    @field:JsonProperty("total_tokens")
    val totalTokens: Int
)
