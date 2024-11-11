package com.kev.chatsample.chat

import jakarta.persistence.Id

class Chat(

    @Id
    var id: String = "",
    val senderId: Long,
    val content: String,
) {

    override fun toString(): String {
        return "Chat(id='$id', senderId=$senderId, content='$content')"
    }
}
