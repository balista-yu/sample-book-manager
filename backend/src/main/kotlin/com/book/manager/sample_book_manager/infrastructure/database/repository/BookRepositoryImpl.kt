package com.book.manager.sample_book_manager.infrastructure.database.repository

import com.book.manager.sample_book_manager.domain.model.Book
import com.book.manager.sample_book_manager.domain.model.BookWithRental
import com.book.manager.sample_book_manager.domain.model.Rental
import com.book.manager.sample_book_manager.domain.repository.BookRepository
import com.book.manager.sample_book_manager.infrastructure.database.mapper.custom.BookWithRentalMapper
import com.book.manager.sample_book_manager.infrastructure.database.mapper.custom.select
import com.book.manager.sample_book_manager.infrastructure.database.mapper.custom.selectByPrimaryKey
import com.book.manager.sample_book_manager.infrastructure.database.record.custom.BookWithRentalRecord
import com.book.manager.sample_book_manager.presentation.aop.LoggingAdvice
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@Repository
class BookRepositoryImpl(
    private val bookWithRentalMapper: BookWithRentalMapper,
    private val jdbcTemplate: JdbcTemplate,
    private val logger: Logger = LoggerFactory.getLogger(LoggingAdvice::class.java),
) : BookRepository {
    override fun findAllWithRental(): List<BookWithRental> {
        return bookWithRentalMapper.select().map { toModel(it) }
    }

    override fun findWithRental(id: Int): BookWithRental? {
        return bookWithRentalMapper.selectByPrimaryKey(id)?.let { toModel(it) }
    }

    override fun register(book: Book) {
        val sql = "INSERT INTO book(id, title, author, release_date) VALUES (?, ?, ?, ?);"
        jdbcTemplate.update(sql, book.id, book.title, book.author, book.releaseDate)
    }

    override fun update(id: Int, title: String?, author: String?, releaseDate: LocalDateTime?) {
        val sql = "UPDATE book SET title = ?, author = ?, release_date = ? WHERE id = ?;"
        jdbcTemplate.update(sql, title, author, releaseDate, id)
    }

    override fun delete(id: Int) {
        val sql = "DELETE FROM book WHERE id = ?"
        jdbcTemplate.update(sql, id)
    }

    private fun toModel(record: BookWithRentalRecord): BookWithRental {
        val book = Book(
            record.id!!,
            record.title!!,
            record.author!!,
            record.releaseDate!!
        )
        val rental = record.operatorId?.let {
            Rental(
                record.id!!,
                record.operatorId!!,
                record.rentalDatetime!!,
                record.returnDeadline!!
            )
        }
        return BookWithRental(book, rental)
    }
}
