package herbaccara.openai.form

import herbaccara.openai.form.validation.ValidationUtils
import java.io.File

data class CreateImageEditForm @JvmOverloads constructor(
    val image: File,
    val mask: File? = null,
    val prompt: String,
    val n: Int? = null,
    val size: String? = null,
    val responseFormat: ImageResponseFormat? = null,
    val user: String? = null
) {
    init {
        ValidationUtils.image(::image)
        ValidationUtils.image(::mask)
        ValidationUtils.lessThan(::prompt, 1000)
        ValidationUtils.between(::n, 1.0, 10.0)
        ValidationUtils.imageSize(::size)
    }
}
