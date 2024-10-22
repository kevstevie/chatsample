package com.kev.chatsample

import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Component

@Component
class ChatRepository(private val mongoTemplate: MongoTemplate) {

    fun save(chat: Chat, chatRoomId: Long) {
        mongoTemplate.insert(chat, "chatroom_$chatRoomId")
    }

    fun findByChatRoomId(chatRoomId: Long): List<Chat> {
        return mongoTemplate.findAll(
            Chat::class.java, "chatroom_$chatRoomId"
        )
    }
}
