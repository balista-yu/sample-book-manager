package com.book.manager.usecase.endRental

import com.book.manager.domain.model.id.OperatorId

data class EndRentalInput(
    val bookId: String,
    val operatorId: OperatorId,
)
