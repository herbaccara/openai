package herbaccara.boot.autoconfigure.openai

import org.springframework.context.annotation.Import
import java.lang.annotation.*

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Import(OpenAiAutoConfiguration::class)
annotation class EnableOpenAi
