package com.book.manager.domain.repository

import com.book.manager.domain.model.Book
import com.book.manager.domain.model.BookWithRental
import java.time.LocalDateTime

interface BookRepository {
    fun findAllWithRental(): List<BookWithRental>
    fun findWithRental(id: Int): BookWithRental?
    fun register(book: Book)
    fun update(id: Int, title: String?, author: String?, releaseDate: LocalDateTime?)
    fun delete(id: Int)
}
