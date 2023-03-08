package herbaccara.boot.autoconfigure.openai

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "openai")
@ConstructorBinding
data class OpenAiProperties(
    val enabled: Boolean = true,
    val rootUri: String = "https://api.openai.com",
    val organizationId: String,
    val secretKey: String
)
