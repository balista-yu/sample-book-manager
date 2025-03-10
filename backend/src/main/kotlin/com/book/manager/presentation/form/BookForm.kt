package com.book.manager.presentation.form

import java.time.LocalDateTime

data class RegisterBookRequest(
    val title: String,
    val author: String,
    val releaseDate: LocalDateTime,
)

data class UpdateBookRequest(
    val id: String,
    val title: String?,
    val author: String?,
    val releaseDate: LocalDateTime?,
)
