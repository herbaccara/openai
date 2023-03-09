package herbaccara.openai;

import herbaccara.openai.model.model.ListModels;
import org.junit.jupiter.api.Test;

public class OpenAiJavaTest {

    @Test
    public void test() {
        final String apiKey = "123123";
        final OpenAiService openAiService = new OpenAiService(apiKey);
        final ListModels listModels = openAiService.listModels();
    }
}
