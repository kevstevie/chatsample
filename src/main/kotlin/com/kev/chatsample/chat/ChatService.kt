package com.kev.chatsample.chat

import org.springframework.stereotype.Service

@Service
class ChatService(private val chatRepository: ChatRepository) {

    fun save(chat: Chat, chatRoomId: Long) {
        chatRepository.save(chat, chatRoomId)
    }

    fun getChats(chatRoomId: Long, lastId: String): List<Chat> {
        return chatRepository.findByChatRoomId(chatRoomId, lastId)
    }
}
