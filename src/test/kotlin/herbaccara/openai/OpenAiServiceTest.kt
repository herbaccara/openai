package herbaccara.openai

import herbaccara.boot.autoconfigure.openai.OpenAiAutoConfiguration
import herbaccara.openai.form.*
import herbaccara.openai.model.chat.completion.Message
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
        val result = openAiService.listModels()
        println(result)
    }

    @Test
    fun model() {
        val model = openAiService.retrieveModel("text-davinci-003")
        println(model)
    }

    @Test
    fun completion() {
        val form = CreateCompletionForm("text-davinci-003", "Say this is a test", maxTokens = 7, temperature = 0)
        val completion = openAiService.createCompletion(form)
        println(completion)
    }

    @Test
    fun chatCompletion() {
        val form = CreateChatCompletionForm("gpt-3.5-turbo", listOf(Message("user", "hello")))
        val chatCompletion = openAiService.createChatCompletion(form)
        println()
    }

    @Test
    fun edit() {
        val form = CreateEditForm("text-davinci-edit-001", "What day of the wek is it?", "Fix the spelling mistakes")
        val edit = openAiService.createEdit(form)
        println()
    }

    @Test
    fun imageGenerationUrl() {
        val form = CreateImageForm("A cute baby sea otter", n = 2, size = "1024x1024", responseFormat = ImageResponseFormat.url)
        val result = openAiService.createImage(form)
        println()
    }

    @Test
    fun imageGenerationB64() {
        val form = CreateImageForm("A cute baby sea otter", n = 2, size = "1024x1024", responseFormat = ImageResponseFormat.b64_json)
        val result = openAiService.createImage(form)
        println()
    }

    @Test
    fun imageEdit() {
        val form = CreateImageEditForm(
            image = File("src/test/resources/apple.png"),
            prompt = "A cute baby sea otter wearing a beret",
            n = 2,
            size = "1024x1024"
        )
        val result = openAiService.createImageEdit(form)
        println()
    }

    @Test
    fun imageVariation() {
        val form = CreateImageVariationForm(
            image = File("src/test/resources/apple.png"),
            n = 2,
            size = "1024x1024"
        )
        val result = openAiService.createImageVariation(form)
        println()
    }

    @Test
    fun embedding() {
        val form = CreateEmbeddingForm(
            "text-embedding-ada-002",
            "The food was delicious and the waiter..."
        )
        val result = openAiService.createEmbedding(form)
        println()
    }

    @Test
    fun files() {
        val result = openAiService.listFiles()
        println()
    }

    @Test
    fun uploadFiles() {
        val form = UploadFileForm(
            File("src/test/resources/test-fine-tuning-data.jsonl"),
            "테스트 파인 튜닝 데이터"
        )
        val uploadFiles = openAiService.uploadFile(form)
        println()
    }

    // Fine-tunes

    @Test
    fun fineTunes() {
        val fineTunes = openAiService.listFineTunes()
        println()
    }

    @Test
    fun moderation() {
        val form = CreateModerationForm(
            "I want to kill them."
        )
        val result = openAiService.createModeration(form)
        println()
    }
}
