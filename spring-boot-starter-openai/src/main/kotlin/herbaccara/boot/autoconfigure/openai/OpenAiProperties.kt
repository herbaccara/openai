package herbaccara.boot.autoconfigure.openai

import herbaccara.openai.OpenAiService
import herbaccara.openai.log.LoggingLevel
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import java.time.Duration

@ConfigurationProperties(prefix = "openai")
@ConstructorBinding
data class OpenAiProperties(
    val enabled: Boolean = true,
    val apiKey: String,
    val rootUri: String = OpenAiService.BASE_URL,
    val timeout: Duration = OpenAiService.DEFAULT_TIMEOUT,
    val validate: Boolean = false,
    val logging: LoggingProperties = LoggingProperties(),
    val proxy: ProxyProperties? = null
) {
    data class ProxyProperties(val host: String, val port: Int)

    data class LoggingProperties(
        val enable: Boolean = false,
        val level: LoggingLevel = LoggingLevel.BASIC
    )
}
