package com.book.manager.domain.repository

import com.book.manager.domain.model.Book
import com.book.manager.domain.model.id.BookId

interface RentalRepository {
    fun startRental(book: Book)
    fun endRental(bookId: BookId)
}
