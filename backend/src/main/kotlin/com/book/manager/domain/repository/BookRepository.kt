package com.book.manager.domain.repository

import com.book.manager.domain.model.entity.Book
import com.book.manager.domain.model.id.BookId
import java.time.LocalDateTime

interface BookRepository {
    fun findAllWithRental(): List<Book>
    fun findWithRental(id: BookId): Book?
    fun register(book: Book)
    fun update(id: BookId, title: String?, author: String?, releaseDate: LocalDateTime?)
    fun delete(id: BookId)
}
