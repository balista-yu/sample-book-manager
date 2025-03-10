package com.book.manager.usecase.updateBook

import com.book.manager.core.domain.model.Id
import com.book.manager.domain.model.entity.Book
import com.book.manager.domain.repository.BookRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.time.LocalDateTime

internal class UpdateBookUseCaseTest {
    private val bookRepository = mock<BookRepository>()

    private val updateBookUseCase = UpdateBookUseCase(bookRepository)

    @Test
    fun testUpdateBook() {
        val bookId = Id.Text("1000")
        val book = Book(bookId, "title", "author", LocalDateTime.now(), null)

        whenever(bookRepository.findWithRental(bookId)).thenReturn(book)
        val title = "title2"
        val author = "author2"

        updateBookUseCase(bookId, title, author, null)

        verify(bookRepository, times(1)).findWithRental(bookId)
        verify(bookRepository, times(1)).update(bookId, title, author, null)
    }

    @Test
    fun testUpdateBookFailedWhenNotExistsBook() {
        val bookId = Id.Text("1000")

        whenever(bookRepository.findWithRental(bookId)).thenReturn(null)

        val exception = Assertions.assertThrows(IllegalArgumentException::class.java) {
            updateBookUseCase(bookId, null, null, null)
        }

        assertThat(exception.message).isEqualTo("Book not found")

        verify(bookRepository, times(1)).findWithRental(bookId)
        verify(bookRepository, times(0)).update(bookId, null, null, null)
    }
}
