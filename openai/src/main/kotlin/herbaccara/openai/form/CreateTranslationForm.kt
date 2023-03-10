package herbaccara.openai.form

import com.toasttab.ksp.builder.annotations.GenerateBuilder
import herbaccara.openai.form.enums.AudioResponseFormat
import herbaccara.openai.form.validation.ValidationUtils
import java.io.File

@GenerateBuilder
data class CreateTranslationForm @JvmOverloads constructor(
    val file: File,
    val model: String = "whisper-1",
    val prompt: String? = null,
    val responseFormat: AudioResponseFormat? = null,
    val temperature: Double? = null
) : Form {

    companion object {

        @JvmStatic
        fun builder(): CreateTranslationFormBuilder = CreateTranslationFormBuilder()
    }

    override fun validate() {
        ValidationUtils.audio(::file)
        ValidationUtils.between(::temperature, 0.0, 1.0)
    }
}
