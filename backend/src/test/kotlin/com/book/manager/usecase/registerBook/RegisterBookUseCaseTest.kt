package com.book.manager.usecase.registerBook

import com.book.manager.core.domain.factory.IdFactory
import com.book.manager.domain.model.entity.Book
import com.book.manager.domain.model.id.BookId
import com.book.manager.domain.repository.BookRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.time.LocalDateTime

internal class RegisterBookUseCaseTest {
    private val bookRepository = mock<BookRepository>()
    private val idFactory = mock<IdFactory>()

    private val registerBookUseCase = RegisterBookUseCase(bookRepository, idFactory)

    @Test
    fun testRegisterBook() {
        val releaseDate = LocalDateTime.now()
        val book = Book(BookId("1000"), "title", "author", releaseDate, null)

        whenever(idFactory.create()).thenReturn("1000")

        val output = registerBookUseCase(
            RegisterBookInput(
                "title",
                "author",
                releaseDate,
            ),
        )
        verify(bookRepository, times(1)).register(book)
        Assertions.assertNotNull(output.bookId.value)
    }
}
