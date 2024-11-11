package com.kev.chatsample.config

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.kev.chatsample.chat.Chat
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig {

    @Bean
    fun connectionFactory() = LettuceConnectionFactory()

    @Bean
    fun objectMapper() = jacksonObjectMapper().registerKotlinModule()

    @Bean
    fun chatRedisTemplate(): RedisTemplate<String, Chat> {
        return RedisTemplate<String, Chat>().apply {
            connectionFactory = connectionFactory()
            keySerializer = StringRedisSerializer()
            valueSerializer = GenericJackson2JsonRedisSerializer(objectMapper())
        }
    }

    @Bean
    fun intRedisTemplate(): RedisTemplate<String, Int> {
        return RedisTemplate<String, Int>().apply {
            connectionFactory = connectionFactory()
            keySerializer = StringRedisSerializer()
            valueSerializer = StringRedisSerializer()
        }
    }
}
