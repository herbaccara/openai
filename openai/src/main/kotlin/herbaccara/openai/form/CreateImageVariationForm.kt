package herbaccara.openai.form

import herbaccara.openai.form.validation.ValidationUtils
import java.io.File

data class CreateImageVariationForm @JvmOverloads constructor(
    val image: File,
    val n: Int? = null,
    val size: String? = null,
    val responseFormat: ImageResponseFormat? = null,
    val user: String? = null
) {
    init {
        ValidationUtils.image(::image)
        ValidationUtils.between(::n, 1.0, 10.0)
        ValidationUtils.imageSize(::size)
    }
}
