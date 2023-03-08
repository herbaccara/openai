package herbaccara.openai

import herbaccara.boot.autoconfigure.openai.OpenAiAutoConfiguration
import herbaccara.openai.form.ChatCompletionForm
import herbaccara.openai.form.CompletionForm
import herbaccara.openai.form.EditForm
import herbaccara.openai.model.Message
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

@SpringBootTest(
    classes = [
        OpenAiAutoConfiguration::class
    ]
)
@TestPropertySource(locations = ["classpath:application.yml"])
class OpenAiServiceTest {

    @Autowired
    lateinit var openAiService: OpenAiService

    @Test
    fun models() {
        val models = openAiService.models()
        println(models)
    }

    @Test
    fun model() {
        val model = openAiService.model("text-davinci-003")
        println(model)
    }

    @Test
    fun completion() {
        val form = CompletionForm("text-davinci-003", "Say this is a test", maxTokens = 7, temperature = 0)
        val completion = openAiService.completion(form)
        println(completion)
    }

    @Test
    fun chatCompletion() {
        val form = ChatCompletionForm("gpt-3.5-turbo", listOf(Message("user", "hello")))
        val chatCompletion = openAiService.chatCompletion(form)
        println()
    }

    @Test
    fun edit() {
        val form = EditForm("text-davinci-edit-001", "What day of the wek is it?", "Fix the spelling mistakes")
        val edit = openAiService.edit(form)
        println()
    }
}
