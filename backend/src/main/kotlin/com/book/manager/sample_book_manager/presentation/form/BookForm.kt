package com.book.manager.sample_book_manager.presentation.form

import com.book.manager.sample_book_manager.domain.model.BookWithRental
import com.book.manager.sample_book_manager.domain.model.Rental
import java.time.LocalDateTime

data class GetBookListResponse(val bookList: List<BookInfo>)

data class BookInfo(
    val id: Int,
    val title: String,
    val author: String,
    val isRental: Boolean
) {
    constructor(model: BookWithRental) : this(
        id = model.book.id,
        title = model.book.title,
        author = model.book.author,
        isRental = model.isRental
    )
}

data class GetBookDetailResponse(
    val id: Int,
    val title: String,
    val author: String,
    val releaseDate: LocalDateTime,
    val rentalInfo: RentalInfo?
) {
    constructor(model: BookWithRental) : this(
        id = model.book.id,
        title = model.book.title,
        author = model.book.author,
        releaseDate = model.book.releaseDate,
        rentalInfo = model.rental?.let { RentalInfo(it) }
    )
}

data class RentalInfo(
    val userId: Int,
    val rentalDatetime: LocalDateTime,
    val returnDeadline: LocalDateTime
) {
    constructor(rental: Rental) : this(
        userId = rental.operatorId,
        rentalDatetime = rental.rentalDatetime,
        returnDeadline = rental.returnDeadline
    )
}

data class RegisterBookRequest(
    val id: Int,
    val title: String,
    val author: String,
    val releaseDate: LocalDateTime
)

data class UpdateBookRequest(
    val id: Int,
    val title: String?,
    val author: String?,
    val releaseDate: LocalDateTime?
)
