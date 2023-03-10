package herbaccara.openai.form

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import herbaccara.openai.form.validation.ValidationUtils

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class CreateCompletionForm @JvmOverloads constructor(
    val model: String,
    val prompt: String,
    val suffix: String? = null,
    @field:JsonProperty("max_tokens") val maxTokens: Int? = null,
    val temperature: Double? = null,
    @field:JsonProperty("top_p") val topP: Int? = null,
    val n: Int? = null,
    val stream: Boolean? = null,
    val logprobs: Int? = null,
    val echo: Boolean? = null,
    val stop: String? = null,
    @field:JsonProperty("presence_penalty") val presencePenalty: Double? = null,
    @field:JsonProperty("frequency_penalty") val frequencyPenalty: Double? = null,
    @field:JsonProperty("best_of") val bestOf: Int? = null,
    @field:JsonProperty("logit_bias") val logitBias: Map<String, Int>? = null,
    val user: String? = null
) : Form {

    companion object {

        @JvmStatic
        fun builder(): Builder = Builder()
    }

    override fun validate() {
        ValidationUtils.between(::temperature, 0.0, 2.0)
        ValidationUtils.between(::presencePenalty, -2.0, 2.0)
        ValidationUtils.between(::frequencyPenalty, -2.0, 2.0)
        if (logitBias != null) {
            for ((k, v) in logitBias) {
                ValidationUtils.between("${::logitBias.name}.$k", v, -100.0, 100.0)
            }
        }
    }

    class Builder {
        private var model: String? = null
        private var prompt: String? = null
        private var suffix: String? = null
        private var maxTokens: Int? = null
        private var temperature: Double? = null
        private var topP: Int? = null
        private var n: Int? = null
        private var stream: Boolean? = null
        private var logprobs: Int? = null
        private var echo: Boolean? = null
        private var stop: String? = null
        private var presencePenalty: Double? = null
        private var frequencyPenalty: Double? = null
        private var bestOf: Int? = null
        private var logitBias: Map<String, Int>? = null
        private var user: String? = null

        fun model(model: String) = apply { this.model = model }
        fun prompt(prompt: String) = apply { this.prompt = prompt }
        fun suffix(suffix: String) = apply { this.suffix = suffix }
        fun maxTokens(maxTokens: Int) = apply { this.maxTokens = maxTokens }
        fun temperature(temperature: Double) = apply { this.temperature = temperature }
        fun topP(topP: Int) = apply { this.topP = topP }
        fun n(n: Int) = apply { this.n = n }
        fun stream(stream: Boolean) = apply { this.stream = stream }
        fun logprobs(logprobs: Int) = apply { this.logprobs = logprobs }
        fun echo(echo: Boolean) = apply { this.echo = echo }
        fun stop(stop: String) = apply { this.stop = stop }
        fun presencePenalty(presencePenalty: Double) = apply { this.presencePenalty = presencePenalty }
        fun frequencyPenalty(frequencyPenalty: Double) = apply { this.frequencyPenalty = frequencyPenalty }
        fun bestOf(bestOf: Int) = apply { this.bestOf = bestOf }
        fun logitBias(logitBias: Map<String, Int>) = apply { this.logitBias = logitBias }
        fun user(user: String) = apply { this.user = user }

        fun build(): CreateCompletionForm {
            return CreateCompletionForm(
                requireNotNull(model) { "\"model\" must not be null" },
                requireNotNull(prompt) { "\"prompt\" must not be null" },
                suffix,
                maxTokens,
                temperature,
                topP,
                n,
                stream,
                logprobs,
                echo,
                stop,
                presencePenalty,
                frequencyPenalty
            )
        }
    }
}
