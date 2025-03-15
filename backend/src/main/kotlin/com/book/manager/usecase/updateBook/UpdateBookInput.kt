package com.book.manager.usecase.updateBook

import java.time.LocalDateTime

data class UpdateBookInput(
    val bookId: String,
    val title: String?,
    val author: String?,
    val releaseDate: LocalDateTime?,
)
