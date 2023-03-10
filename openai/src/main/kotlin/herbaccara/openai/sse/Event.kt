package herbaccara.openai.sse

data class Event<T>(
    val id: String? = null,
    val type: String? = null,
    val data: T? = null,
    val done: Boolean
)
