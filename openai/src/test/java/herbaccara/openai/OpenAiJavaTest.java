package herbaccara.openai;

import herbaccara.openai.form.CreateChatCompletionForm;
import herbaccara.openai.model.chat.completion.Message;
import herbaccara.openai.model.model.ListModels;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class OpenAiJavaTest {

    final String apiKey = "";
    final OpenAiService openAiService = new OpenAiService(apiKey);

    @Test
    public void createChatCompletions() throws InterruptedException {
        CreateChatCompletionForm form = new CreateChatCompletionForm(
                "gpt-3.5-turbo",
                Collections.singletonList(new Message("user", "hello"))
        );

        openAiService.createChatCompletions(form, System.out::println);

        Thread.sleep(60 * 1000);
    }

    @Test
    public void listModels() {
        final ListModels listModels = openAiService.listModels();
    }
}
