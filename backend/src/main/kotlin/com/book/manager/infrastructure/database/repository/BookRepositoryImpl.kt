package com.book.manager.infrastructure.database.repository

import com.book.manager.domain.model.Book
import com.book.manager.domain.repository.BookRepository
import com.book.manager.infrastructure.database.hydrator.BookHydrator
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class BookRepositoryImpl(
    private val jdbcTemplate: JdbcTemplate,
    private val bookHydrator: BookHydrator,
) : BookRepository {
    override fun findAllWithRental(): List<Book> {
        val sql =
            """
            SELECT
                b.id,
                b.title,
                b.author,
                b.release_date,
                r.operator_id,
                r.rental_datetime,
                r.return_deadline
            FROM
                book b
            LEFT JOIN
                rental r
            ON
                b.id = r.book_id
            ;
            """
        return jdbcTemplate.query(sql) { rs, _ -> bookHydrator.hydrate(rs) }
    }

    override fun findWithRental(id: Int): Book? {
        val sql =
            """
            SELECT
                b.id,
                b.title,
                b.author,
                b.release_date,
                r.operator_id,
                r.rental_datetime,
                r.return_deadline
            FROM
                book b
            LEFT JOIN
                rental r
            ON
                b.id = r.book_id
            WHERE
                b.id = ?
            ;
            """
        return jdbcTemplate.query(sql, { rs, _ -> bookHydrator.hydrate(rs) }, id).firstOrNull()
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
}
