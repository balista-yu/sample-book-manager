package com.book.manager.usecase.getBook

import com.book.manager.domain.model.entity.Book
import com.book.manager.domain.model.id.BookId
import com.book.manager.domain.repository.BookRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.time.LocalDateTime

internal class GetBookUseCaseTest {
    private val bookRepository = mock<BookRepository>()

    private val getBookUseCase = GetBookUseCase(bookRepository)

    @Test
    fun testGetBook() {
        val bookId = BookId("1")
        val book = Book(bookId, "title", "author", LocalDateTime.now(), null)

        whenever(bookRepository.findWithRental(bookId)).thenReturn(book)
        val result = getBookUseCase(bookId)
        Assertions.assertThat(book).isEqualTo(result)
    }
}
