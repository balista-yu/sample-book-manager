package com.book.manager.sample_book_manager.infrastructure.database.mapper

import com.book.manager.sample_book_manager.infrastructure.database.record.OperatorRecord
import org.apache.ibatis.annotations.DeleteProvider
import org.apache.ibatis.annotations.InsertProvider
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.SelectProvider
import org.apache.ibatis.annotations.UpdateProvider
import org.apache.ibatis.type.EnumTypeHandler
import org.apache.ibatis.type.JdbcType
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider
import org.mybatis.dynamic.sql.util.SqlProviderAdapter

@Mapper
interface OperatorMapper {
    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    fun count(selectStatement: SelectStatementProvider): Long

    @DeleteProvider(type = SqlProviderAdapter::class, method = "delete")
    fun delete(deleteStatement: DeleteStatementProvider): Int

    @InsertProvider(type = SqlProviderAdapter::class, method = "insert")
    fun insert(insertStatement: InsertStatementProvider<OperatorRecord>): Int

    @InsertProvider(type = SqlProviderAdapter::class, method = "insert")
    fun insertMultiple(multipleInsertStatement: MultiRowInsertStatementProvider<OperatorRecord>): Int

    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @ResultMap("OperatorRecordResult")
    fun selectOne(selectStatement: SelectStatementProvider): OperatorRecord?

    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @Results(
        id = "OperatorRecordResult", value = [
            Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            Result(column = "email", property = "email", jdbcType = JdbcType.VARCHAR),
            Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
            Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            Result(
                column = "role_type",
                property = "roleType",
                typeHandler = EnumTypeHandler::class,
                jdbcType = JdbcType.CHAR
            ),
        ]
    )
    fun selectMany(selectStatement: SelectStatementProvider): List<OperatorRecord>

    @UpdateProvider(type = SqlProviderAdapter::class, method = "update")
    fun update(updateStatement: UpdateStatementProvider): Int
}
