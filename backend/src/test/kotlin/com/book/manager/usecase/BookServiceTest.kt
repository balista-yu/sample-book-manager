package com.book.manager.usecase

import com.book.manager.domain.model.entity.Book
import com.book.manager.domain.model.id.BookId
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
        val book = Book(BookId("1"), "title", "author", LocalDateTime.now(), null)
        val expected = listOf(book)

        whenever(bookRepository.findAllWithRental()).thenReturn(expected)
        val result = bookService.getList()
        Assertions.assertThat(expected).isEqualTo(result)
    }
}
