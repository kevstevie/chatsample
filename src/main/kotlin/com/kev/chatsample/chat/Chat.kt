package com.kev.chatsample.chat

import com.kev.chatsample.util.customFormat
import jakarta.persistence.Id
import java.time.LocalDateTime

class Chat(

    @Id
    val id: String = LocalDateTime.now().customFormat(),
    val senderId: Long,
    val content: String,
) {

    override fun toString(): String {
        return "Chat(id='$id', senderId=$senderId, content='$content')"
    }
}
