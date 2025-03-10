package com.book.manager.usecase.deleteBook

import com.book.manager.domain.model.id.BookId
import com.book.manager.domain.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteBookUseCase(
    private val bookRepository: BookRepository,
) {
    @Transactional
    operator fun invoke(deleteBookInput: DeleteBookInput) {
        val bookId = BookId(deleteBookInput.bookId)
        bookRepository.findWithRental(bookId) ?: throw IllegalArgumentException("Book not found")
        bookRepository.delete(bookId)
    }
}
