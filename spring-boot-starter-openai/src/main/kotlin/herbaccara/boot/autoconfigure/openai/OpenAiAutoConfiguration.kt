package herbaccara.boot.autoconfigure.openai

import herbaccara.openai.OpenAiService
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import java.util.*

@AutoConfiguration
@EnableConfigurationProperties(OpenAiProperties::class)
@ConditionalOnProperty(prefix = "openai", value = ["enabled"], havingValue = "true", matchIfMissing = true)
class OpenAiAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    fun openAiService(properties: OpenAiProperties): OpenAiService {
        if (properties.apiKey.isEmpty()) throw NullPointerException("\"api-key\" must not be null")

        return OpenAiService(
            properties.apiKey,
            properties.rootUri,
            properties.timeout,
            properties.validate,
            properties.logging
        )
    }
}
