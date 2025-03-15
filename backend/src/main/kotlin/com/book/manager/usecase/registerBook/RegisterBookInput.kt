package com.book.manager.usecase.registerBook

import java.time.LocalDateTime

data class RegisterBookInput(
    val title: String,
    val author: String,
    val releaseDate: LocalDateTime,
)
