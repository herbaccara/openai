package herbaccara.openai.form

import herbaccara.openai.form.enums.AudioResponseFormat
import herbaccara.openai.form.validation.ValidationUtils
import java.io.File

data class CreateTranslationForm @JvmOverloads constructor(
    val file: File,
    val model: String = "whisper-1",
    val prompt: String? = null,
    val responseFormat: AudioResponseFormat? = null,
    val temperature: Double? = null
) : Form {

    override fun validate() {
        ValidationUtils.audio(::file)
        ValidationUtils.between(::temperature, 0.0, 1.0)
    }
}
