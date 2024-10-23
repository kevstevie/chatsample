package com.kev.chatsample.chat

import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Component

@Component
class ChatRepository(private val mongoTemplate: MongoTemplate) {

    fun save(chat: Chat, chatRoomId: Long) {
        mongoTemplate.insert(chat, "chatroom_$chatRoomId")
    }

    fun findByChatRoomId(chatRoomId: Long, lastId: String): List<Chat> {
        val query = Query().with(Sort.by(Sort.Direction.DESC, "id"))
            .limit(20)

        if (lastId != "default") {
            query.addCriteria(Criteria.where("id").lt(lastId))
        }
        return mongoTemplate.find(
            query,
            Chat::class.java,
            "chatroom_$chatRoomId"
        )
    }
}