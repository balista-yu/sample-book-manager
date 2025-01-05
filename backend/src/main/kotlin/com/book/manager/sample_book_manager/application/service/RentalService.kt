package com.book.manager.sample_book_manager.application.service

import com.book.manager.sample_book_manager.domain.model.Rental
import com.book.manager.sample_book_manager.domain.repository.BookRepository
import com.book.manager.sample_book_manager.domain.repository.RentalRepository
import com.book.manager.sample_book_manager.domain.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

private const val RENTAL_TERM_DAYS = 14L

@Service
class RentalService(
    private val userRepository: UserRepository,
    private val bookRepository: BookRepository,
    private val rentalRepository: RentalRepository
) {
    @Transactional
    fun startRental(bookId: Int, userId: Int) {
        userRepository.find(userId) ?: throw IllegalArgumentException("User not found")
        val book = bookRepository.findWithRental(bookId) ?: throw IllegalArgumentException("Book not found")

        if (book.isRental) throw IllegalStateException("Book is already rented")

        val rentalDateTime = LocalDateTime.now()
        val returnDeadline = rentalDateTime.plusDays(RENTAL_TERM_DAYS)
        val rental = Rental(bookId, userId, rentalDateTime, returnDeadline)

        rentalRepository.startRental(rental)
    }

    @Transactional
    fun endRental(bookId: Int, userId: Int) {
        userRepository.find(userId) ?: throw IllegalArgumentException("User not found")
        val book = bookRepository.findWithRental(bookId) ?: throw IllegalArgumentException("Book not found")

        if (!book.isRental) throw IllegalStateException("Book is not rented")
        if (book.rental!!.userId != userId) throw IllegalArgumentException("User is not the renter")

        rentalRepository.endRental(bookId)
    }
}
