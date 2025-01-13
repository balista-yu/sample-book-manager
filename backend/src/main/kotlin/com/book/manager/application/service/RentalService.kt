package com.book.manager.application.service

import com.book.manager.domain.model.Rental
import com.book.manager.domain.repository.BookRepository
import com.book.manager.domain.repository.OperatorRepository
import com.book.manager.domain.repository.RentalRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

private const val RENTAL_TERM_DAYS = 14L

@Service
class RentalService(
    private val operatorRepository: OperatorRepository,
    private val bookRepository: BookRepository,
    private val rentalRepository: RentalRepository
) {
    @Transactional
    fun startRental(bookId: Int, operatorId: Int) {
        require(operatorRepository.find(operatorId) != null) { "Operator not found" }
        val book = bookRepository.findWithRental(bookId) ?: throw IllegalArgumentException("Book not found")

        require(!book.isRental) { "Book is already rented" }

        val rentalDateTime = LocalDateTime.now()
        val returnDeadline = rentalDateTime.plusDays(RENTAL_TERM_DAYS)
        val rental = Rental(bookId, operatorId, rentalDateTime, returnDeadline)

        rentalRepository.startRental(rental)
    }

    @Transactional
    fun endRental(bookId: Int, operatorId: Int) {
        require(operatorRepository.find(operatorId) != null) { "Operator not found" }
        val book = bookRepository.findWithRental(bookId) ?: throw IllegalArgumentException("Book not found")

        check(book.isRental) { "Book is not rented" }
        check(book.rental!!.operatorId == operatorId) { "Operator is not the renter" }

        rentalRepository.endRental(bookId)
    }
}