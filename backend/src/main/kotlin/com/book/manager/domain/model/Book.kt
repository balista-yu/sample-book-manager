package com.book.manager.domain.model

import java.time.LocalDateTime

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val releaseDate: LocalDateTime
)
