package com.book.manager.sample_book_manager.domain.repository

import com.book.manager.sample_book_manager.domain.model.Rental

interface RentalRepository {
    fun startRental(rental: Rental)
    fun endRental(bookId: Int)
}
