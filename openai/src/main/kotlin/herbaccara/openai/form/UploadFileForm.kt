package herbaccara.openai.form

import com.toasttab.ksp.builder.annotations.GenerateBuilder
import java.io.File

@GenerateBuilder
data class UploadFileForm(
    val file: File,
    val purpose: String
) : Form {

    companion object {

        @JvmStatic
        fun builder(): UploadFileFormBuilder = UploadFileFormBuilder()
    }

    override fun validate() {
        // nothing
    }
}
