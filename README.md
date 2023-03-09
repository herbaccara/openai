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

## to use
```java
// java
final OpenAiService openAiService = new OpenAiService(apiKey);
final ListModels listModels = openAiService.listModels();
```

```kotlin
// kotlin
val openAiService = OpenAiService(apiKey)
val listModels: ListModels = openAiService.listModels()
```

## application.yml 
```yaml
openai:
  enabled: true
  api-key: your api key
  root-uri: https://api.openai.com
  timeout: 30s
  validate: false
  logging:
    enable: true
    level: BASIC
```

## License
Published under the MIT License