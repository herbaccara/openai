package herbaccara.openai.exception

import herbaccara.openai.model.Error

sealed interface OpenAiException {

    class HttpErrorException @JvmOverloads constructor(
        message: String? = null,
        cause: Throwable? = null,
        val error: Error? = null
    ) : RuntimeException(message, cause), OpenAiException

    class IllegalArgumentException(message: String? = null, cause: Throwable? = null) :
        kotlin.IllegalArgumentException(message, cause), OpenAiException
}
