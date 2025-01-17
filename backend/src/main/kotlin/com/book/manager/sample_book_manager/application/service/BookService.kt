package com.book.manager.sample_book_manager.application.service

import com.book.manager.sample_book_manager.domain.model.BookWithRental
import com.book.manager.sample_book_manager.domain.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    private val bookRepository: BookRepository
) {
    fun getList(): List<BookWithRental> {
        return bookRepository.findAllWithRental()
    }

    fun getDetail(bookId: Int): BookWithRental {
        return bookRepository.findWithRental(bookId) ?: throw IllegalArgumentException("Book not found")
    }
}
