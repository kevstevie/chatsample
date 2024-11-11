package com.kev.chatsample.chat

import com.kev.chatsample.util.customFormat
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.time.LocalDateTime

private const val ID_TICKET = "idTicket"

@Component
class ChatIdGenerator(private val redisTemplate: RedisTemplate<String, Int>) {

    fun generateId(): String {
        val id = redisTemplate.opsForValue().increment(ID_TICKET) ?: 0

        if (id >= 500000) {
            redisTemplate.opsForValue().set(ID_TICKET, 0)
        }

        return "${LocalDateTime.now().customFormat()}${id.toString().padStart(6, '0')}"
    }
}
