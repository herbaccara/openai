package herbaccara.openai.form.validation

import herbaccara.openai.exception.OpenAiException
import java.io.File
import kotlin.reflect.KProperty0

object ValidationUtils {

    private val imageSizes = listOf("256x256", "512x512", "1024x1024")
    private val audioExtensions = listOf("mp3", "mp4", "mpeg", "mpga", "m4a", "wav", "webm")

    fun imageSize(field: KProperty0<String?>) {
        val value = field.get() ?: return
        if (imageSizes.contains(value).not()) {
            throw IllegalArgumentException("\"${field.name}\" must be one of ${imageSizes.joinToString(", ")}")
        }
    }

    fun audio(field: KProperty0<File?>) {
        val value = field.get() ?: return
        if (audioExtensions.contains(value.extension).not()) {
            throw IllegalArgumentException("\"${field.name}\" must be one of these formats: ${audioExtensions.joinToString(", ")}")
        }
    }

    fun between(field: KProperty0<Number?>, start: Double, end: Double) {
        val value = field.get() ?: return
        if (value.toDouble() !in start..end) throw OpenAiException.IllegalArgumentException("\"${field.name}\" must be between $start and $end")
    }

    fun lessThan(field: KProperty0<String?>, limit: Int) {
        val value = field.get() ?: return
        if (value.length > limit) throw IllegalArgumentException("\"${field.name}\" must be less than $limit")
    }

    fun image(field: KProperty0<File?>) {
        val value = field.get() ?: return
        if (value.length() > 4 * 1024 * 1024) throw IllegalArgumentException("\"${field.name}\" must be less than 4MB")
    }
}
