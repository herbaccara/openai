package herbaccara.openai.form

import java.io.File

data class UploadFileForm(val file: File, val purpose: String)
