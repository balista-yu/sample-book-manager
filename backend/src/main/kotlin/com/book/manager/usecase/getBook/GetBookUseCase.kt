package com.book.manager.usecase.getBook

import com.book.manager.domain.model.entity.Book
import com.book.manager.domain.model.id.BookId
import com.book.manager.domain.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class GetBookUseCase(
    private val bookRepository: BookRepository,
) {
    operator fun invoke(getBookInput: GetBookInput): Book =
        bookRepository.findWithRental(BookId(getBookInput.bookId)) ?: throw IllegalArgumentException("Book not found")
}
