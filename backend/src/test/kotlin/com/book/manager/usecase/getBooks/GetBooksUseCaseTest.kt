package com.book.manager.usecase.getBooks

import com.book.manager.domain.model.entity.Book
import com.book.manager.domain.model.id.BookId
import com.book.manager.domain.repository.BookRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.time.LocalDateTime

internal class GetBooksUseCaseTest {
    private val bookRepository = mock<BookRepository>()

    private val getBooksUseCase = GetBooksUseCase(bookRepository)

    @Test
    fun testGetBooks() {
        val book = Book(BookId("1"), "title", "author", LocalDateTime.now(), null)
        val expected = listOf(book)

        whenever(bookRepository.findAllWithRental()).thenReturn(expected)
        val result = getBooksUseCase()
        Assertions.assertThat(expected).isEqualTo(result)
    }
}
