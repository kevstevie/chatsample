package com.kev.chatsample.config

import com.mongodb.client.MongoClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate

@Configuration
class MongoConfig {

    @Bean
    fun mongoTemplate() = MongoTemplate(mongoClient(), "test")

    @Bean
    fun mongoClient() = MongoClients.create("mongodb://localhost:27017/test")
}
