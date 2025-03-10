package com.book.manager.usecase.endRental

import com.book.manager.domain.criteria.OperatorCriteria
import com.book.manager.domain.model.id.BookId
import com.book.manager.domain.repository.BookRepository
import com.book.manager.domain.repository.OperatorRepository
import com.book.manager.domain.repository.RentalRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EndRentalUseCase(
    private val operatorRepository: OperatorRepository,
    private val bookRepository: BookRepository,
    private val rentalRepository: RentalRepository,
) {
    @Transactional
    operator fun invoke(endRentalInput: EndRentalInput) {
        require(
            operatorRepository.find(OperatorCriteria(id = endRentalInput.operatorId)) != null,
        ) { "Operator not found" }
        val bookId = BookId(endRentalInput.bookId)
        val book = bookRepository.findWithRental(bookId) ?: throw IllegalArgumentException("Book not found")

        check(book.isRental) { "Book is not rented" }
        check(book.rental!!.operatorId == endRentalInput.operatorId) { "Operator is not the renter" }

        rentalRepository.endRental(bookId)
    }
}
