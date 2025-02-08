package com.book.manager.sample_book_manager.infrastructure.database.repository

import com.book.manager.sample_book_manager.domain.model.Rental
import com.book.manager.sample_book_manager.domain.repository.RentalRepository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@Repository
class RentalRepositoryImpl(
    private val jdbcTemplate: JdbcTemplate
) : RentalRepository {
    override fun startRental(rental: Rental) {
        val sql = "INSERT INTO rental(book_id, operator_id, rental_datetime, return_deadline) VALUES (?, ?, ?, ?);"
        jdbcTemplate.update(sql, rental.bookId, rental.operatorId, rental.rentalDatetime, rental.returnDeadline)
    }

    override fun endRental(bookId: Int) {
        val sql = "DELETE FROM rental WHERE book_id = ?"
        jdbcTemplate.update(sql, bookId)
    }
}
