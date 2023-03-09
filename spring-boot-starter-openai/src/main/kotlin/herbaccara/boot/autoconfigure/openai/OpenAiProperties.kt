package herbaccara.boot.autoconfigure.openai

import herbaccara.openai.log.Logging
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import java.time.Duration

@ConfigurationProperties(prefix = "openai")
@ConstructorBinding
data class OpenAiProperties(
    val enabled: Boolean = true,
    val apiKey: String,
    val rootUri: String = "https://api.openai.com",
    val timeout: Duration = Duration.ofSeconds(30),
    val validate: Boolean = false,
    val logging: Logging = Logging()
)
