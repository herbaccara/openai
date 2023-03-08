package herbaccara.openai.form

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class CreateFineTuneForm @JvmOverloads constructor(
    @field:JsonProperty("training_file") val trainingFile: String,
    @field:JsonProperty("validation_file") val validationFile: String? = null,
    val model: String? = null,
    @field:JsonProperty("n_epochs") val nEpochs: Int? = null,
    @field:JsonProperty("batch_size") val batchSize: Int? = null,
    @field:JsonProperty("learning_rate_multiplier") val learningRateMultiplier: Double? = null,
    @field:JsonProperty("prompt_loss_weight") val promptLossWeight: Double? = null,
    @field:JsonProperty("compute_classification_metrics") val computeClassificationMetrics: Boolean? = null,
    @field:JsonProperty("classification_n_classes") val classificationNClasses: Int? = null,
    @field:JsonProperty("classification_positive_class") val classificationPositiveClass: String? = null,
    @field:JsonProperty("classification_betas") val classificationBetas: List<Double>? = null,
    val suffix: String? = null
)
