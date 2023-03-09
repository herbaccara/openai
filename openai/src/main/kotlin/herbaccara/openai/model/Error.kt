package herbaccara.openai.model

data class Error(
    val message: String,
    val type: String,
    val param: String,
    val code: String?
)
