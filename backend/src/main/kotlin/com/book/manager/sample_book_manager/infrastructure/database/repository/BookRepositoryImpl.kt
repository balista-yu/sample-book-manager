package com.book.manager.sample_book_manager.infrastructure.database.repository

import com.book.manager.sample_book_manager.domain.model.Book
import com.book.manager.sample_book_manager.domain.model.BookWithRental
import com.book.manager.sample_book_manager.domain.model.Rental
import com.book.manager.sample_book_manager.domain.repository.BookRepository
import com.book.manager.sample_book_manager.infrastructure.database.mapper.BookMapper
import com.book.manager.sample_book_manager.infrastructure.database.mapper.custom.BookWithRentalMapper
import com.book.manager.sample_book_manager.infrastructure.database.mapper.custom.select
import com.book.manager.sample_book_manager.infrastructure.database.mapper.custom.selectByPrimaryKey
import com.book.manager.sample_book_manager.infrastructure.database.mapper.deleteByPrimaryKey
import com.book.manager.sample_book_manager.infrastructure.database.mapper.insert
import com.book.manager.sample_book_manager.infrastructure.database.mapper.updateByPrimaryKeySelective
import com.book.manager.sample_book_manager.infrastructure.database.record.BookRecord
import com.book.manager.sample_book_manager.infrastructure.database.record.custom.BookWithRentalRecord
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@Repository
class BookRepositoryImpl(
    private val bookWithRentalMapper: BookWithRentalMapper,
    private val bookMapper: BookMapper
) : BookRepository {
    override fun findAllWithRental(): List<BookWithRental> {
        return bookWithRentalMapper.select().map { toModel(it) }
    }

    override fun findWithRental(id: Int): BookWithRental? {
        return bookWithRentalMapper.selectByPrimaryKey(id)?.let { toModel(it) }
    }

    override fun register(book: Book) {
        bookMapper.insert(toRecord(book))
    }

    override fun update(id: Int, title: String?, author: String?, releaseDate: LocalDateTime?) {
        bookMapper.updateByPrimaryKeySelective(
            BookRecord(id, title, author, releaseDate)
        )
    }

    override fun delete(id: Int) {
        bookMapper.deleteByPrimaryKey(id)
    }

    private fun toModel(record: BookWithRentalRecord): BookWithRental {
        val book = Book(
            record.id!!,
            record.title!!,
            record.author!!,
            record.releaseDate!!
        )
        val rental = record.userId?.let {
            Rental(
                record.id!!,
                record.userId!!,
                record.rentalDatetime!!,
                record.returnDeadline!!
            )
        }
        return BookWithRental(book, rental)
    }

    private fun toRecord(model: Book): BookRecord {
        return BookRecord(
            model.id,
            model.title,
            model.author,
            model.releaseDate
        )
    }
}
