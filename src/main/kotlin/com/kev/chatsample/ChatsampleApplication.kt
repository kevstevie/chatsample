package com.kev.chatsample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ChatsampleApplication

fun main(args: Array<String>) {
    runApplication<ChatsampleApplication>(*args)
}
