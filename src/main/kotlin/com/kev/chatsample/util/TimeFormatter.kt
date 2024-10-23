package com.kev.chatsample.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.customFormat(): String =
    this.format(DateTimeFormatter.ofPattern("yyMMddHHmmssSSS"))

