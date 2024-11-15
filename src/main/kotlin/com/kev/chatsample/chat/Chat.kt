package com.kev.chatsample.chat

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class Chat(

    @Id
    var id: String = "",
    val chatRoomId: Long,
    val senderId: Long,
    val content: String,
) {

    override fun toString(): String {
        return "Chat(id='$id', senderId=$senderId, content='$content')"
    }
}
