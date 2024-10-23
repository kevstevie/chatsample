package com.kev.chatsample.chat

import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.web.bind.annotation.*

@RestController
class ChatController(
    private val chatService: ChatService,
    private val simpMessageSendingOperations: SimpMessageSendingOperations
) {

    @MessageMapping("/chat/{chatRoomId}")
    fun chat(@DestinationVariable chatRoomId: Long, @RequestBody chatDto: ChatDto) {
        val chat = Chat(senderId = chatDto.senderId, content = chatDto.content)
        simpMessageSendingOperations.convertAndSend(
            "/sub/chat/${chatRoomId}",
            chat
        )
        chatService.save(chat, chatRoomId)
    }

    @GetMapping("/chat/{chatRoomId}")
    fun getChats(@PathVariable chatRoomId: Long, @RequestParam(defaultValue = "default") lastId: String): List<Chat> {
        return chatService.getChats(chatRoomId, lastId)
    }
}
