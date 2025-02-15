package com.book.manager.usecase

import com.book.manager.domain.model.entity.Book
import com.book.manager.domain.model.id.BookId
import com.book.manager.domain.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class AdminBookService(
    private val bookRepository: BookRepository
) {
    @Transactional
    fun register(book: Book) {
        bookRepository.findWithRental(book.id)?.let {
            throw IllegalArgumentException("Book already exists")
        }
        bookRepository.register(book)
    }

    @Transactional
    fun update(bookId: BookId, title: String?, author: String?, releaseDate: LocalDateTime?) {
        bookRepository.findWithRental(bookId) ?: throw IllegalArgumentException("Book not found")
        bookRepository.update(bookId, title, author, releaseDate)
    }

    @Transactional
    fun delete(bookId: BookId) {
        bookRepository.findWithRental(bookId) ?: throw IllegalArgumentException("Book not found")
        bookRepository.delete(bookId)
    }
}
