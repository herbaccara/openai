package herbaccara.openai.model.finetune

import com.fasterxml.jackson.annotation.JsonProperty
import herbaccara.openai.model.file.File

data class FineTune(
    val id: String,
    @field:JsonProperty("object") val `object`: String,
    val model: String,
    @field:JsonProperty("created_at") val createdAt: Long,
    val events: List<Event>,
    @field:JsonProperty("fine_tuned_model") val fineTunedModel: String,
    val hyperparams: Hyperparams,
    @field:JsonProperty("organization_id") val organizationId: String,
    @field:JsonProperty("result_files") val resultFiles: List<File>,
    val status: String,
    @field:JsonProperty("validation_files") val validationFiles: List<File>,
    @field:JsonProperty("training_files") val trainingFiles: List<File>,
    @field:JsonProperty("updated_at") val updatedAt: Long
)
