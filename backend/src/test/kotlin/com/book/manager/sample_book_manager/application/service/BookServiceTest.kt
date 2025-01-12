package com.book.manager.sample_book_manager.application.service

import com.book.manager.sample_book_manager.domain.model.Book
import com.book.manager.sample_book_manager.domain.model.BookWithRental
import com.book.manager.sample_book_manager.domain.repository.BookRepository
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
        val book = Book(1, "title", "author", LocalDateTime.now())
        val bookWithRental = BookWithRental(book, null)
        val expected = listOf(bookWithRental)

        whenever(bookRepository.findAllWithRental()).thenReturn(expected)
        val result = bookService.getList()
        Assertions.assertThat(expected).isEqualTo(result)
    }
}
