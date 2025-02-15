package com.book.manager.infrastructure.database.repository

import com.book.manager.domain.model.entity.Book
import com.book.manager.domain.model.id.BookId
import com.book.manager.domain.repository.RentalRepository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class RentalRepositoryImpl(
    private val jdbcTemplate: JdbcTemplate
) : RentalRepository {
    override fun startRental(book: Book) {
        val sql = "INSERT INTO rental(book_id, operator_id, rental_datetime, return_deadline) VALUES (?, ?, ?, ?);"
        jdbcTemplate.update(
            sql,
            book.id.value,
            book.rental?.operatorId?.value,
            book.rental?.rentalDatetime,
            book.rental?.returnDeadline
        )
    }

    override fun endRental(bookId: BookId) {
        val sql = "DELETE FROM rental WHERE book_id = ?"
        jdbcTemplate.update(sql, bookId.value)
    }
}
