package herbaccara.boot.autoconfigure.openai

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import herbaccara.openai.OpenAiService
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter
import java.nio.charset.StandardCharsets
import java.util.*

@AutoConfiguration
@EnableConfigurationProperties(OpenAiProperties::class)
@ConditionalOnProperty(prefix = "openai", value = ["enabled"], havingValue = "true")
class OpenAiAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    fun objectMapper(): ObjectMapper {
        return jacksonObjectMapper().apply {
            findAndRegisterModules()
        }
    }

    @Bean
    fun openAiService(
        objectMapper: ObjectMapper,
        properties: OpenAiProperties,
        customizers: List<OpenAiRestTemplateBuilderCustomizer>,
        interceptors: List<OpenAiClientHttpRequestInterceptor>
    ): OpenAiService {
        if (properties.secretKey.isEmpty()) throw NullPointerException()

        val restTemplate = RestTemplateBuilder()
            .rootUri(properties.rootUri)
            .additionalInterceptors(
                ClientHttpRequestInterceptor { request, body, execution ->
                    val headers = request.headers
                    headers.setBearerAuth(properties.secretKey)

                    execution.execute(request, body)
                }
            )
            .additionalInterceptors(*interceptors.toTypedArray())
            .defaultMessageConverters()
            .messageConverters(
                StringHttpMessageConverter(StandardCharsets.UTF_8),
                AllEncompassingFormHttpMessageConverter(),
                MappingJackson2HttpMessageConverter(objectMapper)
            )
            .also { builder ->
                for (customizer in customizers) {
                    customizer.customize(builder)
                }
            }
            .build()

        return OpenAiService(restTemplate)
    }
}
