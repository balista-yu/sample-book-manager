package com.book.manager.infrastructure.database.mapper

import org.mybatis.dynamic.sql.SqlTable
import java.sql.JDBCType
import java.time.LocalDateTime

object BookDynamicSqlSupport {
    object Book : SqlTable("book") {
        val id = column<Int>("id", JDBCType.INTEGER)
        val title = column<String>("title", JDBCType.VARCHAR)
        val author = column<String>("author", JDBCType.VARCHAR)
        val releaseDate = column<LocalDateTime>("release_date", JDBCType.TIMESTAMP)
    }
}
