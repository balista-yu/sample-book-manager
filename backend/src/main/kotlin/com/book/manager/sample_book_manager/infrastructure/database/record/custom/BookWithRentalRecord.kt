package com.book.manager.sample_book_manager.infrastructure.database.record.custom

import java.time.LocalDateTime

data class BookWithRentalRecord(
    var id: Int? = null,
    var title: String? = null,
    var author: String? = null,
    var releaseDate: LocalDateTime? = null,
    var operatorId: Int? = null,
    var rentalDatetime: LocalDateTime? = null,
    var returnDeadline: LocalDateTime? = null
)
