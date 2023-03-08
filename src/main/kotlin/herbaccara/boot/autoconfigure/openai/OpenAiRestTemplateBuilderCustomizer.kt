package herbaccara.boot.autoconfigure.openai

import org.springframework.boot.web.client.RestTemplateBuilder

interface OpenAiRestTemplateBuilderCustomizer {

    fun customize(restTemplateBuilder: RestTemplateBuilder)
}
