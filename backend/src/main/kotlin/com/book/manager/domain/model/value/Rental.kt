package com.book.manager.domain.model.value

import com.book.manager.domain.model.id.OperatorId
import java.time.LocalDateTime

data class Rental(
    val operatorId: OperatorId,
    val rentalDatetime: LocalDateTime,
    val returnDeadline: LocalDateTime,
)
