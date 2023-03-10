# OpenAI
OpenAI

Made with API version 2023-3-9.

## How to
To get a Git project into your build:

*Step 1*. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

*Step 2*. Add the dependency

```groovy
dependencies {
    implementation 'com.github.herbaccara.openai:openai:Tag'
    
    // if you want to use spring boot auto-configuration
    implementation 'com.github.herbaccara.openai:spring-boot-starter-openai:Tag'
}
```

## Supported APIs
- [Models](https://platform.openai.com/docs/api-reference/models)
- [Completions](https://platform.openai.com/docs/api-reference/completions)
- [Chat Completions](https://platform.openai.com/docs/api-reference/chat/create)
- [Edits](https://platform.openai.com/docs/api-reference/edits)
- [Embeddings](https://platform.openai.com/docs/api-reference/embeddings)
- [Files](https://platform.openai.com/docs/api-reference/files)
- [Fine-tunes](https://platform.openai.com/docs/api-reference/fine-tunes)
- [Images](https://platform.openai.com/docs/api-reference/images)
- [Moderations](https://platform.openai.com/docs/api-reference/moderations)

## To use
```java
// java
final OpenAiService openAiService = OpenAiService
        .builder()
        .apiKey(apiKey)
        .validate(true)
        .logging(new Logging(true, LoggingLevel.BASIC))
        .baseUrl("https://api.openai.com")
        .timeout(Duration.ofSeconds(30))
        .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port)))
        .build();

final ListModels listModels = openAiService.listModels();
```

```kotlin
// kotlin
val openAiService = OpenAiService(apiKey)
val listModels: ListModels = openAiService.listModels()
```

## Spring boot configuration
### @EnableOpenAi
```java
@EnableOpenAi
public class SpringBootApp {
    
    @Autowired
    private OpenAiService openAiService;
}
```

### application.yml
```yaml
openai:
  api-key: your api key
  root-uri: https://api.openai.com # optional
  timeout: 30s # optional
  validate: false # optional
  proxy: # optional
    host: 123.123.123.123
    port: 123
  logging: # optional
    enable: true
    level: BASIC
```

## License
Published under the MIT License