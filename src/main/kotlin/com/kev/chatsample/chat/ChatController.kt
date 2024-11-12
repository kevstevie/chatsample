package com.kev.chatsample.chat

import com.kev.chatsample.pubsub.RedisChatPublisher
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.web.bind.annotation.*

@RestController
class ChatController(
    private val chatService: ChatService,
    private val redisChatPublisher: RedisChatPublisher
) {

    @MessageMapping("/chat/{chatRoomId}")
    fun chat(@DestinationVariable chatRoomId: Long, @RequestBody chatDto: ChatDto) {
        val chat = Chat(senderId = chatDto.senderId, content = chatDto.content)
        redisChatPublisher.publish(chatDto)
        chatService.save(chat, chatRoomId)
    }

    @GetMapping("/chat/{chatRoomId}")
    fun getChats(
        @PathVariable chatRoomId: Long,
        @RequestParam(defaultValue = Double.MAX_VALUE.toString()) lastId: String
    ): List<Chat> {
        return chatService.getChats(chatRoomId, lastId)
    }
}
