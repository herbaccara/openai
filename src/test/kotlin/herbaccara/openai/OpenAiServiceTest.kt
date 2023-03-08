package herbaccara.openai

import herbaccara.boot.autoconfigure.openai.OpenAiAutoConfiguration
import herbaccara.openai.form.*
import herbaccara.openai.model.Message
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import java.io.File

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
        val result = openAiService.models()
        println(result)
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

    @Test
    fun imageGenerationUrl() {
        val form = ImageGenerationForm("A cute baby sea otter", n = 2, size = "1024x1024", responseFormat = ImageResponseFormat.url)
        val result = openAiService.imageGeneration(form)
        println()
    }

    @Test
    fun imageGenerationB64() {
        val form = ImageGenerationForm("A cute baby sea otter", n = 2, size = "1024x1024", responseFormat = ImageResponseFormat.b64_json)
        val result = openAiService.imageGeneration(form)
        println()
    }

    @Test
    fun imageEdit() {
        val form = ImageEditForm(
            image = File("src/test/resources/apple.png"),
            prompt = "A cute baby sea otter wearing a beret",
            n = 2,
            size = "1024x1024"
        )
        val result = openAiService.imageEdit(form)
        println()
    }

    @Test
    fun imageVariation() {
        val form = ImageVariationForm(
            image = File("src/test/resources/apple.png"),
            n = 2,
            size = "1024x1024"
        )
        val result = openAiService.imageVariation(form)
        println()
    }

    @Test
    fun embedding() {
        val form = EmbeddingForm(
            "text-embedding-ada-002",
            "The food was delicious and the waiter..."
        )
        val result = openAiService.embedding(form)
        println()
    }

    @Test
    fun moderation() {
        val form = ModerationForm(
            "I want to kill them."
        )
        val result = openAiService.moderation(form)
        println()
    }
}
