package com.book.manager.presentation.caster

import com.book.manager.domain.model.entity.Book
import com.book.manager.domain.model.value.Rental
import java.time.LocalDateTime

data class GetBookListResponse(val bookList: List<BookResponse>)

data class BookResponse(
    val id: String,
    val title: String,
    val author: String,
    val isRental: Boolean,
) {
    constructor(model: Book) : this(
        id = model.id.value,
        title = model.title,
        author = model.author,
        isRental = model.isRental,
    )
}

data class GetBookResponse(
    val id: String,
    val title: String,
    val author: String,
    val releaseDate: LocalDateTime,
    val rental: BookRental?,
) {
    constructor(model: Book) : this(
        id = model.id.value,
        title = model.title,
        author = model.author,
        releaseDate = model.releaseDate,
        rental = model.rental?.let { BookRental(it) },
    )
}

data class BookRental(
    val userId: Int,
    val rentalDatetime: LocalDateTime,
    val returnDeadline: LocalDateTime,
) {
    constructor(rental: Rental) : this(
        userId = rental.operatorId.value,
        rentalDatetime = rental.rentalDatetime,
        returnDeadline = rental.returnDeadline,
    )
}
