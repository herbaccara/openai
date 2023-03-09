package herbaccara.openai.model.chat.completion

import herbaccara.openai.form.enums.Role

data class Message(val role: String, val content: String) {

    constructor(role: Role, content: String) : this(role.name, content)
}
