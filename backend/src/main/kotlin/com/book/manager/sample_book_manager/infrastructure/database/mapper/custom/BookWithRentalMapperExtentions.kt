package com.book.manager.sample_book_manager.infrastructure.database.mapper.custom

import com.book.manager.sample_book_manager.infrastructure.database.mapper.BookDynamicSqlSupport.Book
import com.book.manager.sample_book_manager.infrastructure.database.mapper.BookDynamicSqlSupport.Book.id
import com.book.manager.sample_book_manager.infrastructure.database.mapper.BookDynamicSqlSupport.Book.title
import com.book.manager.sample_book_manager.infrastructure.database.mapper.BookDynamicSqlSupport.Book.author
import com.book.manager.sample_book_manager.infrastructure.database.mapper.BookDynamicSqlSupport.Book.releaseDate
import com.book.manager.sample_book_manager.infrastructure.database.mapper.RentalDynamicSqlSupport.Rental
import com.book.manager.sample_book_manager.infrastructure.database.mapper.RentalDynamicSqlSupport.Rental.bookId
import com.book.manager.sample_book_manager.infrastructure.database.mapper.RentalDynamicSqlSupport.Rental.rentalDatetime
import com.book.manager.sample_book_manager.infrastructure.database.mapper.RentalDynamicSqlSupport.Rental.returnDeadline
import com.book.manager.sample_book_manager.infrastructure.database.mapper.RentalDynamicSqlSupport.Rental.operatorId
import com.book.manager.sample_book_manager.infrastructure.database.record.custom.BookWithRentalRecord
import org.mybatis.dynamic.sql.SqlBuilder.equalTo
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.SqlBuilder.on
import org.mybatis.dynamic.sql.SqlBuilder.select
import org.mybatis.dynamic.sql.render.RenderingStrategies.MYBATIS3

private val columnList = listOf(
    id,
    title,
    author,
    releaseDate,
    operatorId,
    rentalDatetime,
    returnDeadline
)

fun BookWithRentalMapper.select(): List<BookWithRentalRecord> {
    val selectStatement = select(columnList)
        .from(Book, "b")
        .leftJoin(Rental, on(id, equalTo(bookId)))
        .build()
        .render(MYBATIS3)

    return selectMany(selectStatement)
}

fun BookWithRentalMapper.selectByPrimaryKey(id_: Int): BookWithRentalRecord? {
    val selectStatement = select(columnList)
        .from(Book, "b")
        .leftJoin(Rental, on(id, equalTo(bookId)))
        .where(id, isEqualTo(id_))
        .build()
        .render(MYBATIS3)

    return selectOne(selectStatement)
}
