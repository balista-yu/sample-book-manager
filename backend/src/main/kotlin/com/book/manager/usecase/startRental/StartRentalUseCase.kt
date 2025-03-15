package com.book.manager.usecase.startRental

import com.book.manager.domain.criteria.OperatorCriteria
import com.book.manager.domain.model.id.BookId
import com.book.manager.domain.model.value.Rental
import com.book.manager.domain.repository.BookRepository
import com.book.manager.domain.repository.OperatorRepository
import com.book.manager.domain.repository.RentalRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class StartRentalUseCase(
    private val operatorRepository: OperatorRepository,
    private val bookRepository: BookRepository,
    private val rentalRepository: RentalRepository,
) {
    @Transactional
    operator fun invoke(startRentalInput: StartRentalInput) {
        val bookId = BookId(startRentalInput.bookId)
        require(
            operatorRepository.find(OperatorCriteria(id = startRentalInput.operatorId)) != null,
        ) { "Operator not found" }
        val book = bookRepository.findWithRental(bookId) ?: throw IllegalArgumentException("Book not found")

        require(!book.isRental) { "Book is already rented" }

        val rentalDateTime = LocalDateTime.now()
        val returnDeadline = rentalDateTime.plusDays(RENTAL_TERM_DAYS)
        val rental = Rental(startRentalInput.operatorId, rentalDateTime, returnDeadline)

        val newBook = book.copy(rental = rental)

        rentalRepository.startRental(newBook)
    }

    companion object {
        private const val RENTAL_TERM_DAYS = 14L
    }
}
