package com.kev.chatsample.chat

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class ChatBufferRepository(
    private val redisTemplate: RedisTemplate<String, Chat>,
    private val chatIdGenerator: ChatIdGenerator
) {

    fun save(chat: Chat, chatRoomId: Long) {
        chat.id = chatIdGenerator.generateId()
        redisTemplate.opsForZSet().add("chat:$chatRoomId", chat, chat.id.toDouble())
    }

    fun findByChatRoomId(chatRoomId: Long, lastId: String): List<Chat> {
        return redisTemplate.opsForZSet()
            .reverseRangeByScore("chat:$chatRoomId", Double.MIN_VALUE, lastId.toDouble(), 0, 20)
            ?.toList() ?: emptyList()
    }
}
