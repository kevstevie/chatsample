package com.kev.chatsample.batch

import com.kev.chatsample.chat.ChatRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.TimeUnit

@Component
class BatchChatPersistManager(private val chatRepository: ChatRepository) {

    @Transactional
    @Scheduled(fixedDelay = 5, timeUnit = TimeUnit.MINUTES)
    fun persist() {
        chatRepository.flush()
    }
}
