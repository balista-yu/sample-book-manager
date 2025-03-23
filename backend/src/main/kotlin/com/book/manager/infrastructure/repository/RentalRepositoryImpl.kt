package com.book.manager.infrastructure.repository

import com.book.manager.domain.model.entity.Book
import com.book.manager.domain.model.id.BookId
import com.book.manager.domain.repository.RentalRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import javax.sql.DataSource

@Repository
class RentalRepositoryImpl(
    @Qualifier("firstDataSource")
    private val firstDataSource: DataSource,
) : RentalRepository {
    override fun startRental(book: Book) {
        val firstDataJdbc = NamedParameterJdbcTemplate(firstDataSource)
        val sql =
            """
                INSERT INTO rental(
                    book_id,
                    operator_id,
                    rental_datetime,
                    return_deadline
                ) VALUES (
                    :bookId,
                    :operatorId,
                    :rentalDatetime,
                    :returnDeadline
                )
            """
        val params = mutableMapOf<String, Any>()
        params["bookId"] = book.id.value
        params["operatorId"] = book.rental?.operatorId?.value as Any
        params["rentalDatetime"] = book.rental.rentalDatetime as Any
        params["returnDeadline"] = book.rental.returnDeadline as Any
        firstDataJdbc.update(
            sql,
            params,
        )
    }

    override fun endRental(bookId: BookId) {
        val firstDataJdbc = NamedParameterJdbcTemplate(firstDataSource)
        val sql = "DELETE FROM rental WHERE book_id = :bookId"
        val params = mutableMapOf<String, Any>()
        params["bookId"] = bookId.value
        firstDataJdbc.update(sql, params)
    }
}
