package com.kev.chatsample.chat

import org.springframework.stereotype.Service

@Service
class ChatService(private val chatRepository: ChatRepository) {

    fun save(chat: Chat) {
        chatRepository.save(chat)
    }

    fun getChats(chatRoomId: Long, lastId: String): List<Chat> {
        return chatRepository.findByChatRoomId(chatRoomId, lastId)
    }
}
