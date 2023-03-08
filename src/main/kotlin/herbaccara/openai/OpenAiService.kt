package herbaccara.openai

import org.springframework.web.client.RestTemplate

class OpenAiService(
    private val restTemplate: RestTemplate
)
