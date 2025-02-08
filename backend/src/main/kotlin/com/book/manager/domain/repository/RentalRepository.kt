package com.book.manager.domain.repository

import com.book.manager.domain.model.Book

interface RentalRepository {
    fun startRental(book: Book)
    fun endRental(bookId: Int)
}
