package herbaccara.boot.autoconfigure.openai

import herbaccara.openai.OpenAiService
import herbaccara.openai.log.Logging
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import java.net.InetSocketAddress
import java.net.Proxy
import java.util.*

@AutoConfiguration
@EnableConfigurationProperties(OpenAiProperties::class)
@ConditionalOnProperty(prefix = "openai", value = ["enabled"], havingValue = "true", matchIfMissing = true)
class OpenAiAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    fun openAiService(properties: OpenAiProperties): OpenAiService {
        if (properties.apiKey.isEmpty()) throw NullPointerException("\"api-key\" must not be null")

        val proxy = properties.proxy?.let {
            Proxy(Proxy.Type.HTTP, InetSocketAddress(it.host, it.port))
        }

        return OpenAiService(
            properties.apiKey,
            properties.rootUri,
            properties.timeout,
            properties.validate,
            properties.logging.let { Logging(it.enable, it.level) },
            proxy
        )
    }
}
