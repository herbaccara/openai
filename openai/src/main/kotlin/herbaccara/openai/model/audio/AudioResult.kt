package herbaccara.openai.model.audio

import com.fasterxml.jackson.annotation.JsonProperty

data class AudioResult(
    val json: String?,
    val text: String?,
    val srt: String?,
    @field:JsonProperty("verbose_json")
    val verboseJson: String?,
    val vtt: String?
)
