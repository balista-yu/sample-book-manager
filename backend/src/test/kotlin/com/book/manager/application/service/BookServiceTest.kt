package com.book.manager.application.service

import com.book.manager.domain.model.Book
import com.book.manager.domain.repository.BookRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.time.LocalDateTime

internal class BookServiceTest {
    private val bookRepository = mock<BookRepository>()

    private val bookService = BookService(bookRepository)

    @Test
    fun `getList when book list is exist then return list`() {
        val book = Book(1, "title", "author", LocalDateTime.now(), null)
        val expected = listOf(book)

        whenever(bookRepository.findAllWithRental()).thenReturn(expected)
        val result = bookService.getList()
        Assertions.assertThat(expected).isEqualTo(result)
    }
}
