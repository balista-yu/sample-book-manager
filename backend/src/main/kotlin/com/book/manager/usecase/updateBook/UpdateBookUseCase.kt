package com.book.manager.usecase.updateBook

import com.book.manager.domain.model.id.BookId
import com.book.manager.domain.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class UpdateBookUseCase(
    private val bookRepository: BookRepository
) {
    @Transactional
    operator fun invoke(bookId: BookId, title: String?, author: String?, releaseDate: LocalDateTime?) {
        bookRepository.findWithRental(bookId) ?: throw IllegalArgumentException("Book not found")
        bookRepository.update(bookId, title, author, releaseDate)
    }
}
