package herbaccara.openai;

import herbaccara.openai.form.CreateChatCompletionForm;
import herbaccara.openai.log.Logging;
import herbaccara.openai.log.LoggingLevel;
import herbaccara.openai.model.chat.completion.Message;
import herbaccara.openai.model.chat.completion.chunk.ChatCompletionChunk;
import herbaccara.openai.model.model.ListModels;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.time.Duration;
import java.util.Collections;
import java.util.stream.Collectors;

public class OpenAiJavaTest {

    final String apiKey = "";

    final OpenAiService openAiService = OpenAiService.builder()
            .apiKey(apiKey)
            .validate(true)
            .logging(new Logging(true, LoggingLevel.BASIC))
            .baseUrl("https://api.openai.com")
            .timeout(Duration.ofSeconds(60))
            .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("123", 123)))
            .build();

    @Test
    public void createChatCompletions() throws InterruptedException {
        CreateChatCompletionForm form = new CreateChatCompletionForm(
                "gpt-3.5-turbo",
                Collections.singletonList(new Message("user", "hello"))
        );

        openAiService.createChatCompletions(form, chatCompletionChunkEvent -> {
            final ChatCompletionChunk data = chatCompletionChunkEvent.getData();
            if (data != null) {
                final String collect = data.getChoices()
                        .stream()
                        .map(it -> it.getDelta().getContent())
                        .collect(Collectors.joining(""));

                System.out.print(collect);
            }
        });

        Thread.sleep(60 * 1000);
    }

    @Test
    public void listModels() {
        final ListModels listModels = openAiService.listModels();
    }
}
