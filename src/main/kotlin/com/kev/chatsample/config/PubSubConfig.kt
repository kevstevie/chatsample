package com.kev.chatsample.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.kev.chatsample.chat.ChatDto
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class PubSubConfig(
    private val redisConnectionFactory: LettuceConnectionFactory,
    private val objectMapper: ObjectMapper,
) {

    @Bean
    fun chatDtoRedisTemplate() = RedisTemplate<String, ChatDto>().apply {
        connectionFactory = redisConnectionFactory
        keySerializer = StringRedisSerializer()
        valueSerializer = GenericJackson2JsonRedisSerializer(objectMapper)
    }

    @Bean
    fun topic() = ChannelTopic("chat")

    @Bean
    fun listenerContainer() = RedisMessageListenerContainer().apply {
        setConnectionFactory(redisConnectionFactory)
    }
}
