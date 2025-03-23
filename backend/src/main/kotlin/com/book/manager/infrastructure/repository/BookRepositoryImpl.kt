package com.book.manager.infrastructure.repository

import com.book.manager.domain.criteria.BookCriteria
import com.book.manager.domain.model.entity.Book
import com.book.manager.domain.model.id.BookId
import com.book.manager.domain.repository.BookRepository
import com.book.manager.infrastructure.hydrator.BookHydrator
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import javax.sql.DataSource

@Repository
class BookRepositoryImpl(
    private val bookHydrator: BookHydrator,
    @Qualifier("firstDataSource")
    private val firstDataSource: DataSource,
    @Qualifier("secondDataSource")
    private val secondDataSource: DataSource,
) : BookRepository {
    override fun findAllWithRental(criteria: BookCriteria?): List<Book> {
        val firstDataJdbc = NamedParameterJdbcTemplate(firstDataSource)
        val sql = StringBuilder(
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
            """,
        )
        val params = mutableMapOf<String, Any>()

        if (criteria?.id != null) {
            sql.append(" WHERE b.id = :id")
            params["id"] = criteria.id.value
        }

        return firstDataJdbc.query(sql.toString(), params) { rs, _ -> bookHydrator.hydrate(rs) }
    }

    override fun findWithRental(id: BookId): Book? = findAllWithRental(BookCriteria(id = id)).firstOrNull()

    override fun register(book: Book) {
        val firstDataJdbc = NamedParameterJdbcTemplate(firstDataSource)
        val sql = StringBuilder(
            "INSERT INTO book(id, title, author, release_date) VALUES (:id, :title, :author, :releaseDate)",
        )
        val params = mutableMapOf<String, Any>()
        params["id"] = book.id.value
        params["title"] = book.title
        params["author"] = book.author
        params["releaseDate"] = book.releaseDate

        firstDataJdbc.update(sql.toString(), params)

        // 分散トランザクションの検証のため別DBにも書き込み
        val secondDataJdbc = NamedParameterJdbcTemplate(secondDataSource)
        secondDataJdbc.update(sql.toString(), params)
    }

    override fun update(id: BookId, title: String?, author: String?, releaseDate: LocalDateTime?) {
        val firstDataJdbc = NamedParameterJdbcTemplate(firstDataSource)
        val sql = "UPDATE book SET title = :title, author = :author, release_date = :releaseDate WHERE id = :id"
        val params = mutableMapOf<String, Any>()
        params["id"] = id.value
        params["title"] = title as Any
        params["author"] = author as Any
        params["releaseDate"] = releaseDate as Any
        firstDataJdbc.update(sql, params)
    }

    override fun delete(id: BookId) {
        val firstDataJdbc = NamedParameterJdbcTemplate(firstDataSource)
        val sql = "DELETE FROM book WHERE id = :id"
        val params = mutableMapOf<String, Any>()
        params["id"] = id.value
        firstDataJdbc.update(sql, params)
    }
}
