package herbaccara.openai

import herbaccara.openai.exception.OpenAiException
import herbaccara.openai.log.Logging
import java.net.Proxy
import java.time.Duration

class OpenAiServiceBuilder {

    private var apiKey: String? = null
    private var baseUrl: String = OpenAiService.BASE_URL
    private var timeout: Duration = OpenAiService.DEFAULT_TIMEOUT
    private var validate: Boolean = false
    private var logging: Logging = Logging()
    private var proxy: Proxy? = null

    fun apiKey(apiKey: String): OpenAiServiceBuilder {
        return apply { this.apiKey = apiKey }
    }

    fun baseUrl(baseUrl: String): OpenAiServiceBuilder {
        return apply { this.baseUrl = baseUrl }
    }

    fun timeout(timeout: Duration): OpenAiServiceBuilder {
        return apply { this.timeout = timeout }
    }

    fun validate(validate: Boolean): OpenAiServiceBuilder {
        return apply { this.validate = validate }
    }

    fun logging(logging: Logging): OpenAiServiceBuilder {
        return apply { this.logging = logging }
    }

    fun proxy(proxy: Proxy): OpenAiServiceBuilder {
        return apply { this.proxy = proxy }
    }

    fun build(): OpenAiService {
        if (apiKey == null) throw OpenAiException.IllegalArgumentException("\"api-key\" must not be null")
        return OpenAiService(apiKey!!, baseUrl, timeout, validate, logging, proxy)
    }
}
