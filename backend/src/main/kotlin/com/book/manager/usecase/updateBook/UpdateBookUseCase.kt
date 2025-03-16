package com.book.manager.usecase.updateBook

import com.book.manager.domain.model.id.BookId
import com.book.manager.domain.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UpdateBookUseCase(
    private val bookRepository: BookRepository,
) {
    @Transactional
    operator fun invoke(updateBookInput: UpdateBookInput) {
        val bookId = BookId(updateBookInput.bookId)
        bookRepository.findWithRental(bookId) ?: throw IllegalArgumentException("Book not found")
        bookRepository.update(bookId, updateBookInput.title, updateBookInput.author, updateBookInput.releaseDate)
    }
}
