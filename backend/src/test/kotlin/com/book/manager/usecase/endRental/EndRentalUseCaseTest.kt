package com.book.manager.usecase.endRental

import com.book.manager.core.domain.model.Id
import com.book.manager.core.enum.RoleTypes
import com.book.manager.domain.criteria.OperatorCriteria
import com.book.manager.domain.model.entity.Book
import com.book.manager.domain.model.entity.Operator
import com.book.manager.domain.model.value.Rental
import com.book.manager.domain.model.value.RoleType
import com.book.manager.domain.repository.BookRepository
import com.book.manager.domain.repository.OperatorRepository
import com.book.manager.domain.repository.RentalRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.time.LocalDateTime

internal class EndRentalUseCaseTest {
    private val operatorRepository = mock<OperatorRepository>()
    private val bookRepository = mock<BookRepository>()
    private val rentalRepository = mock<RentalRepository>()

    private val endRentalUseCase = EndRentalUseCase(operatorRepository, bookRepository, rentalRepository)

    @Test
    fun testEndRental() {
        val operatorId = Id.Numeric(100)
        val bookId = Id.Text("1000")
        val operator = Operator(operatorId, "test@test.com", "pass", "kotlin", RoleType(RoleTypes.GENERAL))
        val rental = Rental(operatorId, LocalDateTime.now(), LocalDateTime.MAX)
        val book = Book(bookId, "title", "author", LocalDateTime.now(), rental)

        whenever(operatorRepository.find(OperatorCriteria(id = operatorId))).thenReturn(operator)
        whenever(bookRepository.findWithRental(bookId)).thenReturn(book)

        endRentalUseCase(EndRentalInput("1000", operatorId))

        verify(operatorRepository).find(OperatorCriteria(id = operatorId))
        verify(bookRepository).findWithRental(bookId)
        verify(rentalRepository).endRental(bookId)
    }

    @Test
    fun testEndRentalFailedWhenNotRental() {
        val operatorId = Id.Numeric(100)
        val bookId = Id.Text("1000")
        val operator = Operator(operatorId, "test@test.com", "pass", "kotlin", RoleType(RoleTypes.GENERAL))
        val book = Book(bookId, "title", "author", LocalDateTime.now(), null)

        whenever(operatorRepository.find(OperatorCriteria(id = operatorId))).thenReturn(operator)
        whenever(bookRepository.findWithRental(bookId)).thenReturn(book)

        val exception = Assertions.assertThrows(IllegalStateException::class.java) {
            endRentalUseCase(EndRentalInput("1000", operatorId))
        }

        assertThat(exception.message).isEqualTo("Book is not rented")

        verify(operatorRepository).find(OperatorCriteria(id = operatorId))
        verify(bookRepository).findWithRental(bookId)
        verify(rentalRepository, times(0)).endRental(any())
    }
}
