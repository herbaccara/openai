package herbaccara.openai.form

import herbaccara.openai.form.validation.ValidationUtils
import java.io.File

data class CreateTranscriptionForm @JvmOverloads constructor(
    val file: File,
    val model: String = "whisper-1",
    val prompt: String? = null,
    val responseFormat: AudioResponseFormat? = null,
    val temperature: Double? = null,
    val language: String? = null
) {
    init {
        ValidationUtils.audio(::file)
        ValidationUtils.between(::temperature, 0.0, 1.0)
    }
}
