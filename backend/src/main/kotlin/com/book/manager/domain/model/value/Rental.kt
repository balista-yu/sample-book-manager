package com.book.manager.domain.model.value

import java.time.LocalDateTime

data class Rental(
    val operatorId: Int,
    val rentalDatetime: LocalDateTime,
    val returnDeadline: LocalDateTime
)
