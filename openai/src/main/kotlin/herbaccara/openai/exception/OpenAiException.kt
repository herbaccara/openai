package herbaccara.openai.exception

import herbaccara.openai.model.Error

class OpenAiException @JvmOverloads constructor(
    message: String? = null,
    cause: Throwable? = null,
    val error: Error? = null
) : RuntimeException(message, cause)
