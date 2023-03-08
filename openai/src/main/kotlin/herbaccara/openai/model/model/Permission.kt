package herbaccara.openai.model.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Permission(
    val id: String,
    @field:JsonProperty("object")
    val `object`: String,
    val created: Int,
    @field:JsonProperty("allow_create_engine")
    val allowCreateEngine: Boolean,
    @field:JsonProperty("allow_sampling")
    val allowSampling: Boolean,
    @field:JsonProperty("allow_logprobs")
    val allowLogprobs: Boolean,
    @field:JsonProperty("allow_search_indices")
    val allowSearchIndices: Boolean,
    @field:JsonProperty("allow_view")
    val allowView: Boolean,
    @field:JsonProperty("allow_fine_tuning")
    val allowFineTuning: Boolean,
    val organization: String,
    val group: String?,
    @field:JsonProperty("is_blocking")
    val isBlocking: Boolean
)
