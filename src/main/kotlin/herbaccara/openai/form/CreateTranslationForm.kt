package herbaccara.openai.form

import java.io.File

data class CreateTranslationForm @JvmOverloads constructor(
    val file: File,
    val model: String,
    val prompt: String? = null,
    val responseFormat: AudioResponseFormat? = null,
    val temperature: Int? = null
)
