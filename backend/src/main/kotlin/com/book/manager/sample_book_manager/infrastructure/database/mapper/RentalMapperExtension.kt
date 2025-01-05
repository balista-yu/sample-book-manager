package com.book.manager.sample_book_manager.infrastructure.database.mapper

import com.book.manager.sample_book_manager.infrastructure.database.mapper.BookDynamicSqlSupport.Book.id
import com.book.manager.sample_book_manager.infrastructure.database.mapper.RentalDynamicSqlSupport.Rental
import com.book.manager.sample_book_manager.infrastructure.database.mapper.RentalDynamicSqlSupport.Rental.bookId
import com.book.manager.sample_book_manager.infrastructure.database.mapper.RentalDynamicSqlSupport.Rental.rentalDatetime
import com.book.manager.sample_book_manager.infrastructure.database.mapper.RentalDynamicSqlSupport.Rental.returnDeadline
import com.book.manager.sample_book_manager.infrastructure.database.mapper.RentalDynamicSqlSupport.Rental.userId
import com.book.manager.sample_book_manager.infrastructure.database.record.RentalRecord
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

fun RentalMapper.count(completer: CountCompleter) = countFrom(this::count, Rental, completer)

fun RentalMapper.delete(completer: DeleteCompleter) = deleteFrom(this::delete, Rental, completer)

fun RentalMapper.deleteByPrimaryKey(bookId_: Int) = delete { where { id isEqualTo bookId_ } }

fun RentalMapper.insertOne(record: RentalRecord) = insert(this::insert, record, Rental) {
    map(bookId).toProperty("bookId")
    map(userId).toProperty("userId")
    map(rentalDatetime).toProperty("rentalDatetime")
    map(returnDeadline).toProperty("returnDeadline")
}

fun RentalMapper.insertMultiple(records: Collection<RentalRecord>) =
    insertMultiple(this::insertMultiple, records, Rental) {
        map(bookId).toProperty("bookId")
        map(userId).toProperty("userId")
        map(rentalDatetime).toProperty("rentalDatetime")
        map(returnDeadline).toProperty("returnDeadline")
    }

fun RentalMapper.insertMultiple(vararg records: RentalRecord) = insertMultiple((records.toList()))

fun RentalMapper.insertSelective(record: RentalRecord) = insert(this::insert, record, Rental) {
    map(bookId).toPropertyWhenPresent("bookId", record::bookId)
    map(userId).toPropertyWhenPresent("userId", record::userId)
    map(rentalDatetime).toPropertyWhenPresent("rentalDatetime", record::rentalDatetime)
    map(returnDeadline).toPropertyWhenPresent("returnDeadline", record::returnDeadline)
}

private val columnList = listOf(bookId, userId, rentalDatetime, returnDeadline)

fun RentalMapper.selectOne(completer: SelectCompleter) = selectOne(this::selectOne, columnList, Rental, completer)

fun RentalMapper.select(completer: SelectCompleter) = selectList(this::selectMany, columnList, Rental, completer)

fun RentalMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, Rental, completer)

fun RentalMapper.selectByPrimaryKey(bookId_: Int) = selectOne { where { id isEqualTo bookId_ } }

fun RentalMapper.update(completer: UpdateCompleter) = update(this::update, Rental, completer)

fun KotlinUpdateBuilder.updateAllColumns(record: RentalRecord) = apply {
    set(bookId).equalToWhenPresent(record::bookId)
    set(userId).equalToWhenPresent(record::userId)
    set(rentalDatetime).equalToWhenPresent(record::rentalDatetime)
    set(returnDeadline).equalToWhenPresent(record::returnDeadline)
}

fun KotlinUpdateBuilder.updateSelectiveColumns(record: RentalRecord) = apply {
    set(bookId).equalToWhenPresent(record::bookId)
    set(userId).equalToWhenPresent(record::userId)
    set(rentalDatetime).equalToWhenPresent(record::rentalDatetime)
    set(returnDeadline).equalToWhenPresent(record::returnDeadline)
}

fun RentalMapper.updateByPrimaryKey(record: RentalRecord) = update {
    set(userId).equalToWhenPresent(record::userId)
    set(rentalDatetime).equalToWhenPresent(record::rentalDatetime)
    set(returnDeadline).equalToWhenPresent(record::returnDeadline)
    where { bookId isEqualToWhenPresent record.bookId }
}

fun RentalMapper.updateByPrimaryKeySelective(record: RentalRecord) = update {
    set(userId).equalToWhenPresent(record::userId)
    set(rentalDatetime).equalToWhenPresent(record::rentalDatetime)
    set(returnDeadline).equalToWhenPresent(record::returnDeadline)
    where { bookId isEqualToWhenPresent record.bookId }
}
