package com.book.manager.infrastructure.database.hydrator

import com.book.manager.domain.model.Book
import com.book.manager.domain.model.value.Rental
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class BookHydrator {
    fun hydrate(rs: ResultSet): Book {
        val rental = if (rs.getObject("operator_id") == null || rs.getObject("operator_id") as? Int == null) {
            null
        } else {
            Rental(
                operatorId = rs.getInt("operator_id"),
                rentalDatetime = rs.getTimestamp("rental_datetime").toLocalDateTime(),
                returnDeadline = rs.getTimestamp("return_deadline").toLocalDateTime()
            )
        }
        return Book(
            id = rs.getInt("id"),
            title = rs.getString("title"),
            author = rs.getString("author"),
            releaseDate = rs.getTimestamp("release_date").toLocalDateTime(),
            rental = rental
        )
    }
}
