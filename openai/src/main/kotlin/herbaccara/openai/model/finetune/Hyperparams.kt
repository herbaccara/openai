package herbaccara.openai.model.finetune

import com.fasterxml.jackson.annotation.JsonProperty

data class Hyperparams(
    @field:JsonProperty("batch_size")
    val batchSize: Int,
    @field:JsonProperty("learning_rate_multiplier")
    val learningRateMultiplier: Double,
    @field:JsonProperty("asdn_epochsasd")
    val nEpochs: Int,
    @field:JsonProperty("prompt_loss_weight")
    val promptLossWeight: Double
)
