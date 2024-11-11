package com.kev.chatsample.chat

import org.springframework.stereotype.Component

@Component
class ChatRepository(
    private val chatBufferRepository: ChatBufferRepository,
    private val chatPersistenceRepository: ChatPersistenceRepository
) {

    fun save(chat: Chat, chatRoomId: Long) {
        chatBufferRepository.save(chat, chatRoomId)
    }

    fun findByChatRoomId(chatRoomId: Long, lastId: String): List<Chat> {
        chatBufferRepository.findByChatRoomId(chatRoomId, lastId).let {
            if (it.isEmpty()) {
                return chatPersistenceRepository.findByChatRoomId(chatRoomId, lastId)
            }
            return it
        }
    }
}
