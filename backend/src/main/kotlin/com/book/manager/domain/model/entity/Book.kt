package com.book.manager.domain.model.entity

import com.book.manager.domain.model.id.BookId
import com.book.manager.domain.model.value.Rental
import java.time.LocalDateTime

data class Book(
    val id: BookId,
    val title: String,
    val author: String,
    val releaseDate: LocalDateTime,
    val rental: Rental?,
) {
    val isRental: Boolean
        get() = rental != null
}
