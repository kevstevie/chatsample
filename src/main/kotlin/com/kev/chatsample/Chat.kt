package com.kev.chatsample

import jakarta.persistence.Id
import java.time.LocalDateTime

class Chat(

    @Id
    val id: String = LocalDateTime.now().toString(),
    val senderId: Long,
    val content: String,
) {

}
