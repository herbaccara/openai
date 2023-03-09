package herbaccara.openai

import herbaccara.openai.form.*
import herbaccara.openai.form.enums.ImageResponseFormat
import herbaccara.openai.model.chat.completion.Message
import org.junit.jupiter.api.Test
import java.io.File

class OpenAiTest {

    val apiKey = ""
    val openAiService = OpenAiService(apiKey)

    @Test
    fun listModels() {
        val listModels = openAiService.listModels()
        println(listModels)
    }

    @Test
    fun retrieveModel() {
        val model = openAiService.retrieveModel("text-davinci-003")
        println()
    }

    @Test
    fun createCompletion() {
        val form = CreateCompletionForm("text-davinci-003", "Say this is a test", maxTokens = 7, temperature = 0.0)
        val completion = openAiService.createCompletion(form)
        println(completion)
    }

    @Test
    fun createChatCompletion() {
        val form = CreateChatCompletionForm("gpt-3.5-turbo", listOf(Message("user", "hello")))
        val chatCompletion = openAiService.createChatCompletion(form)
        println()
    }

    @Test
    fun createEdit() {
        val form = CreateEditForm("text-davinci-edit-001", "What day of the wek is it?", "Fix the spelling mistakes")
        val edit = openAiService.createEdit(form)
        println()
    }

    @Test
    fun createImage() {
        val form = CreateImageForm(
            "A cute baby sea otter",
            n = 2,
            size = "1024x1024",
            responseFormat = ImageResponseFormat.url
        )
        val result = openAiService.createImage(form)
        println()
    }

    @Test
    fun createImageEdit() {
        val form = CreateImageEditForm(
            image = File("src/test/resources/penguin.png"),
            prompt = "A cute baby sea otter wearing a beret",
            n = 2,
            size = "1024x1024"
        )

        val result = openAiService.createImageEdit(form)
        println()
    }

    @Test
    fun createTranscription() {
        val form = CreateTranscriptionForm(
            File("src/test/resources/file_example_MP3_700KB.mp3"),
            "whisper-1"
        )

        val result = openAiService.createTranscription(form)
        println()
    }
}
