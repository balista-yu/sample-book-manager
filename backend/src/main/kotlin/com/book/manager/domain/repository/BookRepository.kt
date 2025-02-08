package com.book.manager.domain.repository

import com.book.manager.domain.model.Book
import java.time.LocalDateTime

interface BookRepository {
    fun findAllWithRental(): List<Book>
    fun findWithRental(id: Int): Book?
    fun register(book: Book)
    fun update(id: Int, title: String?, author: String?, releaseDate: LocalDateTime?)
    fun delete(id: Int)
}
