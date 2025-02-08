package com.book.manager.application.service

import com.book.manager.domain.model.Book
import com.book.manager.domain.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    private val bookRepository: BookRepository
) {
    fun getList(): List<Book> {
        return bookRepository.findAllWithRental()
    }

    fun getDetail(bookId: Int): Book {
        return bookRepository.findWithRental(bookId) ?: throw IllegalArgumentException("Book not found")
    }
}
