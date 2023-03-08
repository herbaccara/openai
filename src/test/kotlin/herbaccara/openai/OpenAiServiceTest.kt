package herbaccara.openai

import herbaccara.boot.autoconfigure.openai.OpenAiAutoConfiguration
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

@SpringBootTest(
    classes = [
        OpenAiAutoConfiguration::class
    ]
)
@TestPropertySource(locations = ["classpath:application.yml"])
class OpenAiServiceTest {

    @Test
    fun test() {
    }
}
