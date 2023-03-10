package herbaccara.openai.form

import com.toasttab.ksp.builder.annotations.GenerateBuilder
import herbaccara.openai.form.enums.ImageResponseFormat
import herbaccara.openai.form.validation.ValidationUtils
import java.io.File

@GenerateBuilder
data class CreateImageEditForm @JvmOverloads constructor(
    val image: File,
    val mask: File? = null,
    val prompt: String,
    val n: Int? = null,
    val size: String? = null,
    val responseFormat: ImageResponseFormat? = null,
    val user: String? = null
) : Form {

    companion object {

        @JvmStatic
        fun builder(): CreateImageEditFormBuilder = CreateImageEditFormBuilder()
    }

    override fun validate() {
        ValidationUtils.image(::image)
        ValidationUtils.image(::mask)
        ValidationUtils.lessThan(::prompt, 1000)
        ValidationUtils.between(::n, 1.0, 10.0)
        ValidationUtils.imageSize(::size)
    }
}
