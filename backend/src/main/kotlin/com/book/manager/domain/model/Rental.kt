package com.book.manager.domain.model

import java.time.LocalDateTime

data class Rental(
    val bookId: Int,
    val operatorId: Int,
    val rentalDatetime: LocalDateTime,
    val returnDeadline: LocalDateTime
)
