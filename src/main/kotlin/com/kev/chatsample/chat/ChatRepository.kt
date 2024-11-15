package com.kev.chatsample.chat

import org.springframework.stereotype.Component

@Component
class ChatRepository(
    private val chatBufferRepository: ChatBufferRepository,
    private val chatPersistenceRepository: ChatPersistenceRepository
) {

    fun save(chat: Chat) {
        chatBufferRepository.save(chat)
    }

    fun findByChatRoomId(chatRoomId: Long, lastId: String): List<Chat> {
        chatBufferRepository.findByChatRoomId(chatRoomId, lastId).let {
            if (it.isEmpty()) {
                return chatPersistenceRepository.findByChatRoomId(chatRoomId, lastId)
            }
            return it
        }
    }

    fun flush() {
        val chats = chatBufferRepository.getAllBufferedChat()
        val lastId = chats.last().id

        chatPersistenceRepository.saveAll(chats)
        chatBufferRepository.deleteLowerIds(lastId)
    }
}
