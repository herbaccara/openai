package herbaccara.openai.sse

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import java.util.function.Consumer

class CompletionEventSourceListener<T>(
    private val objectMapper: ObjectMapper,
    private val block: Consumer<Event<T>>
) : EventSourceListener() {

    override fun onEvent(eventSource: EventSource, id: String?, type: String?, data: String) {
        val event = if (data == "[DONE]") {
            Event(done = true)
        } else {
            try {
                val value: T = objectMapper.readValue(data, object : TypeReference<T>() {})
                Event(id, type, value, false)
            } catch (e: Exception) {
                println(data)
                throw e
            }
        }

        block.accept(event)

        super.onEvent(eventSource, id, type, data)
    }
}
