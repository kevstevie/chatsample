package com.kev.chatsample.chat

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component

@Component
interface ChatPersistenceRepository : CrudRepository<Chat, String> {

    @Query(
        """
            select c from Chat c
            where c.chatRoomId = :chatRoomId
            and c.id < :lastId
            order by c.id desc
            limit 20
        """
    )
    fun findByChatRoomId(chatRoomId: Long, lastId: String): List<Chat>
}
