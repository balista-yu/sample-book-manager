package com.book.manager.sample_book_manager.infrastructure.database.mapper

import com.book.manager.sample_book_manager.infrastructure.database.mapper.BookDynamicSqlSupport.Book
import com.book.manager.sample_book_manager.infrastructure.database.mapper.BookDynamicSqlSupport.Book.id
import com.book.manager.sample_book_manager.infrastructure.database.mapper.BookDynamicSqlSupport.Book.title
import com.book.manager.sample_book_manager.infrastructure.database.mapper.BookDynamicSqlSupport.Book.author
import com.book.manager.sample_book_manager.infrastructure.database.mapper.BookDynamicSqlSupport.Book.releaseDate
import com.book.manager.sample_book_manager.infrastructure.database.record.BookRecord
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

fun BookMapper.count(completer: CountCompleter) = countFrom(this::count, Book, completer)

fun BookMapper.delete(completer: DeleteCompleter) = deleteFrom(this::delete, Book, completer)

fun BookMapper.deleteByPrimaryKey(id_: Int) = delete { where{ id isEqualTo id_} }

fun BookMapper.insert(record: BookRecord) = insert(this::insert, record, Book) {
    map(id).toProperty("id")
    map(title).toProperty("title")
    map(author).toProperty("author")
    map(releaseDate).toProperty("releaseDate")
}

fun BookMapper.insertMultiple(records: Collection<BookRecord>) = insertMultiple(this::insertMultiple, records, Book) {
    map(id).toProperty("id")
    map(title).toProperty("title")
    map(author).toProperty("author")
    map(releaseDate).toProperty("releaseDate")
}

fun BookMapper.insertMultiple(vararg records: BookRecord) = insertMultiple((records.toList()))

fun BookMapper.insertSelective(record: BookRecord) = insert(this::insert, record, Book) {
    map(id).toPropertyWhenPresent("id", record::id)
    map(title).toPropertyWhenPresent("title", record::title)
    map(author).toPropertyWhenPresent("author", record::author)
    map(releaseDate).toPropertyWhenPresent("releaseDate", record::releaseDate)
}

private val columnList = listOf(id, title, author, releaseDate)

fun BookMapper.selectOne(completer: SelectCompleter) = selectOne(this::selectOne, columnList, Book, completer)

fun BookMapper.select(completer: SelectCompleter) = selectList(this::selectMany, columnList, Book, completer)

fun BookMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, Book, completer)

fun BookMapper.selectByPrimaryKey(id_: Int) = selectOne { where{ id isEqualTo id_} }

fun BookMapper.update(completer: UpdateCompleter) = update(this::update, Book, completer)

fun KotlinUpdateBuilder.updateAllColumns(record: BookRecord) = apply {
    set(id).equalToWhenPresent(record::id)
    set(title).equalToWhenPresent(record::title)
    set(author).equalToWhenPresent(record::author)
    set(releaseDate).equalToWhenPresent(record::releaseDate)
}

fun KotlinUpdateBuilder.updateSelectiveColumns(record: BookRecord) = apply {
    set(id).equalToWhenPresent(record::id)
    set(title).equalToWhenPresent(record::title)
    set(author).equalToWhenPresent(record::author)
    set(releaseDate).equalToWhenPresent(record::releaseDate)
}

fun BookMapper.updateByPrimaryKey(record: BookRecord) = update {
    set(title).equalToWhenPresent(record::title)
    set(author).equalToWhenPresent(record::author)
    set(releaseDate).equalToWhenPresent(record::releaseDate)
    where { id isEqualToWhenPresent record.id }
}

fun BookMapper.updateByPrimaryKeySelective(record: BookRecord) = update {
    set(title).equalToWhenPresent(record::title)
    set(author).equalToWhenPresent(record::author)
    set(releaseDate).equalToWhenPresent(record::releaseDate)
    where { id isEqualToWhenPresent record.id }
}
