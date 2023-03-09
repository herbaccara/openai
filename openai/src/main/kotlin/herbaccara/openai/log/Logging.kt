package herbaccara.openai.log

data class Logging @JvmOverloads constructor(
    val enable: Boolean = false,
    val level: LoggingLevel = LoggingLevel.BASIC
)
