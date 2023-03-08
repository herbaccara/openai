package herbaccara.openai.form

import java.io.File

data class ImageVariationForm @JvmOverloads constructor(
    val image: File,
    val n: Int? = null,
    val size: String? = null,
    val responseFormat: ImageResponseFormat? = null,
    val user: String? = null
)
