package com.book.manager.sample_book_manager.infrastructure.database.record

import java.time.LocalDateTime

data class RentalRecord(
    var bookId: Int? = null,
    var userId: Int? = null,
    var rentalDatetime: LocalDateTime? = null,
    var returnDeadline: LocalDateTime? = null
)
