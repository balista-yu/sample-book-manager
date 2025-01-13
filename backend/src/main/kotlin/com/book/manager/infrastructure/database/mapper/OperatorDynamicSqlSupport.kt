package com.book.manager.infrastructure.database.mapper

import com.book.manager.domain.enum.RoleType
import org.mybatis.dynamic.sql.SqlTable
import java.sql.JDBCType

object OperatorDynamicSqlSupport {
    object Operator : SqlTable("operator") {
        val id = column<Int>("id", JDBCType.INTEGER)
        val email = column<String>("email", JDBCType.VARCHAR)
        val password = column<String>("password", JDBCType.VARCHAR)
        val name = column<String>("name", JDBCType.VARCHAR)
        val roleType = column<RoleType>("role_type", JDBCType.CHAR, "org.apache.ibatis.type.EnumTypeHandler")
    }
}
