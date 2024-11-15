package com.kev.chatsample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class ChatsampleApplication

fun main(args: Array<String>) {
    runApplication<ChatsampleApplication>(*args)
}
