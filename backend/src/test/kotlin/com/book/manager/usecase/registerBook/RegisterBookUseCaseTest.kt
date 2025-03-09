package com.book.manager.usecase.registerBook

import com.book.manager.core.domain.model.Id
import com.book.manager.domain.model.entity.Book
import com.book.manager.domain.repository.BookRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.time.LocalDateTime

internal class RegisterBookUseCaseTest {
    private val bookRepository = mock<BookRepository>()

    private val registerBookUseCase = RegisterBookUseCase(bookRepository)

    @Test
    fun testRegisterBook() {
        val bookId = Id.Text("1000")
        val book = Book(bookId, "title", "author", LocalDateTime.now(), null)

        whenever(bookRepository.findWithRental(bookId)).thenReturn(null)

        registerBookUseCase(book)

        verify(bookRepository, times(1)).findWithRental(bookId)
        verify(bookRepository, times(1)).register(book)
    }

    @Test
    fun testRegisterBookFailedWhenAlreadyRegistered() {
        val bookId = Id.Text("1000")
        val book = Book(bookId, "title", "author", LocalDateTime.now(), null)

        whenever(bookRepository.findWithRental(bookId)).thenReturn(book)

        val exception = Assertions.assertThrows(IllegalArgumentException::class.java) {
            registerBookUseCase(book)
        }

        assertThat(exception.message).isEqualTo("Book already exists")

        verify(bookRepository, times(1)).findWithRental(bookId)
        verify(bookRepository, times(0)).register(any())
    }
}
