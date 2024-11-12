package com.kev.chatsample.pubsub

import com.fasterxml.jackson.databind.ObjectMapper
import com.kev.chatsample.chat.ChatDto
import jakarta.annotation.PostConstruct
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.stereotype.Component

@Component
class RedisSubscriber(
    private val objectMapper: ObjectMapper,
    private val simpMessageSendingOperations: SimpMessageSendingOperations,
    private val messageListenerContainer: RedisMessageListenerContainer,
    private val topic: ChannelTopic
) : MessageListener {

    override fun onMessage(message: Message, pattern: ByteArray?) {
        val msg = objectMapper.readValue(StringRedisSerializer().deserialize(message.body), ChatDto::class.java)

        simpMessageSendingOperations.convertAndSend("/sub/chat/${msg.chatRoomId}", msg)
    }

    @PostConstruct
    fun subscribe() {
        messageListenerContainer.addMessageListener(this, topic)
    }
}
