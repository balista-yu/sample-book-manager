package com.book.manager.domain.model

import com.book.manager.domain.model.value.Rental
import java.time.LocalDateTime

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val releaseDate: LocalDateTime,
    val rental: Rental?
) {
    val isRental: Boolean
        get() = rental != null
}
