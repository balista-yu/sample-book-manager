package com.book.manager.usecase.getBooks

import com.book.manager.domain.model.entity.Book
import com.book.manager.domain.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class GetBooksUseCase(
    private val bookRepository: BookRepository
) {
    operator fun invoke(): List<Book> {
        return bookRepository.findAllWithRental()
    }
}
