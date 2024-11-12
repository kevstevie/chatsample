package com.kev.chatsample.pubsub

import com.kev.chatsample.chat.ChatDto
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.stereotype.Component

@Component
class RedisChatPublisher(
    private val redisTemplate: RedisTemplate<String, ChatDto>,
    private val topic: ChannelTopic
) {

    fun publish(chat: ChatDto) {
        redisTemplate.convertAndSend(topic.topic, chat)
    }
}
