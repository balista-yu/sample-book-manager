package com.book.manager.application.service

import com.book.manager.core.enum.RoleTypes
import com.book.manager.domain.model.Book
import com.book.manager.domain.model.Operator
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

internal class RentalServiceTest {
    private val operatorRepository = mock<OperatorRepository>()
    private val bookRepository = mock<BookRepository>()
    private val rentalRepository = mock<RentalRepository>()

    private val rentalService = RentalService(operatorRepository, bookRepository, rentalRepository)

    @Test
    fun `endRental when book is rental then delete to rental`() {
        val operatorId = 100
        val bookId = 1000
        val operator = Operator(operatorId, "test@test.com", "pass", "kotlin", RoleType(RoleTypes.GENERAL))
        val rental = Rental(operatorId, LocalDateTime.now(), LocalDateTime.MAX)
        val book = Book(bookId, "title", "author", LocalDateTime.now(), rental)

        whenever(operatorRepository.find(any() as Int)).thenReturn(operator)
        whenever(bookRepository.findWithRental(any())).thenReturn(book)

        rentalService.endRental(bookId, operatorId)

        verify(operatorRepository).find(operatorId)
        verify(bookRepository).findWithRental(bookId)
        verify(rentalRepository).endRental(bookId)
    }

    @Test
    fun `endRental when book is not rental then throw exception`() {
        val operatorId = 100
        val bookId = 1000
        val operator = Operator(operatorId, "test@test.com", "pass", "kotlin", RoleType(RoleTypes.GENERAL))
        val book = Book(bookId, "title", "author", LocalDateTime.now(), null)

        whenever(operatorRepository.find(any() as Int)).thenReturn(operator)
        whenever(bookRepository.findWithRental(any())).thenReturn(book)

        val exception = Assertions.assertThrows(IllegalStateException::class.java) {
            rentalService.endRental(bookId, operatorId)
        }

        assertThat(exception.message).isEqualTo("Book is not rented")

        verify(operatorRepository).find(operatorId)
        verify(bookRepository).findWithRental(bookId)
        verify(rentalRepository, times(0)).endRental(any())
    }
}
