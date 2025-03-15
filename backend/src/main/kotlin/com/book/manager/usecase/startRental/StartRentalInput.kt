package com.book.manager.usecase.startRental

import com.book.manager.domain.model.id.OperatorId

data class StartRentalInput(
    val bookId: String,
    val operatorId: OperatorId,
)
