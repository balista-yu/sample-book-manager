package com.book.manager.usecase

import com.book.manager.domain.model.entity.Book
import com.book.manager.domain.model.id.BookId
import com.book.manager.domain.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    private val bookRepository: BookRepository
) {
    fun getList(): List<Book> {
        return bookRepository.findAllWithRental()
    }

    fun getDetail(bookId: BookId): Book {
        return bookRepository.findWithRental(bookId) ?: throw IllegalArgumentException("Book not found")
    }
}
