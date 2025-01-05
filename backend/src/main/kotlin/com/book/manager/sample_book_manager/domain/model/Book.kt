package com.book.manager.sample_book_manager.domain.model

import java.time.LocalDateTime

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val releaseDate: LocalDateTime
)
