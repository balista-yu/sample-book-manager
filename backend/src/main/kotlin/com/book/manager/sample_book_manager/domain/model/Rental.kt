package com.book.manager.sample_book_manager.domain.model

import java.time.LocalDateTime

data class Rental(
    val bookId: Int,
    val userId: Int,
    val rentalDatetime: LocalDateTime,
    val returnDeadline: LocalDateTime
)
