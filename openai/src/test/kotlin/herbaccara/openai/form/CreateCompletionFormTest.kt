package herbaccara.openai.form

import org.junit.jupiter.api.Test

class CreateCompletionFormTest {

    @Test
    fun test() {
        val build = CreateCompletionForm.builder()
            .model("asdasd")
            .prompt("ttttttttt")
            .build()

        println(build)
    }
}
