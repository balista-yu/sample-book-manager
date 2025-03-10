package com.book.manager.usecase.deleteBook

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

internal class DeleteBookUseCaseTest {
    private val bookRepository = mock<BookRepository>()

    private val deleteBookUseCase = DeleteBookUseCase(bookRepository)

    @Test
    fun testDeleteBook() {
        val bookId = Id.Text("1000")
        val book = Book(bookId, "title", "author", LocalDateTime.now(), null)

        whenever(bookRepository.findWithRental(bookId)).thenReturn(book)

        deleteBookUseCase(bookId)

        verify(bookRepository, times(1)).findWithRental(bookId)
        verify(bookRepository, times(1)).delete(bookId)
    }

    @Test
    fun testDeleteBookFailedWhenNotExistsBook() {
        val bookId = Id.Text("1000")

        whenever(bookRepository.findWithRental(bookId)).thenReturn(null)

        val exception = Assertions.assertThrows(IllegalArgumentException::class.java) {
            deleteBookUseCase(bookId)
        }

        assertThat(exception.message).isEqualTo("Book not found")

        verify(bookRepository, times(1)).findWithRental(bookId)
        verify(bookRepository, times(0)).delete(bookId)
    }
}
