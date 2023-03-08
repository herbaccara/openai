package herbaccara.openai.form

import java.io.File

data class CreateImageEditForm @JvmOverloads constructor(
    val image: File,
    val mask: File? = null,
    val prompt: String,
    val n: Int? = null,
    val size: String? = null,
    val responseFormat: ImageResponseFormat? = null,
    val user: String? = null
)
