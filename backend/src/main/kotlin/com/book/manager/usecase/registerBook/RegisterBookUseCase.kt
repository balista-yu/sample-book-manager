package com.book.manager.usecase.registerBook

import com.book.manager.domain.model.entity.Book
import com.book.manager.domain.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RegisterBookUseCase(
    private val bookRepository: BookRepository
) {
    @Transactional
    operator fun invoke(book: Book) {
        bookRepository.findWithRental(book.id)?.let {
            throw IllegalArgumentException("Book already exists")
        }
        bookRepository.register(book)
    }
}
