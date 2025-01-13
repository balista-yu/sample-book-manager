package com.book.manager.infrastructure.database.mapper

import org.mybatis.dynamic.sql.SqlTable
import java.sql.JDBCType
import java.time.LocalDateTime

object RentalDynamicSqlSupport {
    object Rental : SqlTable("rental") {
        val bookId = column<Int>("book_id", JDBCType.INTEGER)
        val operatorId = column<Int>("operator_id", JDBCType.INTEGER)
        val rentalDatetime = column<LocalDateTime>("rental_datetime", JDBCType.TIMESTAMP)
        val returnDeadline = column<LocalDateTime>("return_deadline", JDBCType.TIMESTAMP)
    }
}
