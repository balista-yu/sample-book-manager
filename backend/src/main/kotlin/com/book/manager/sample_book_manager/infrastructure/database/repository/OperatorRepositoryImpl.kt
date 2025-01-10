package com.book.manager.sample_book_manager.infrastructure.database.repository

import com.book.manager.sample_book_manager.domain.model.Operator
import com.book.manager.sample_book_manager.domain.repository.OperatorRepository
import com.book.manager.sample_book_manager.infrastructure.database.mapper.OperatorDynamicSqlSupport
import com.book.manager.sample_book_manager.infrastructure.database.mapper.OperatorMapper
import com.book.manager.sample_book_manager.infrastructure.database.mapper.selectByPrimaryKey
import com.book.manager.sample_book_manager.infrastructure.database.record.OperatorRecord
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.render.RenderingStrategies.MYBATIS3
import org.mybatis.dynamic.sql.select.SelectDSL
import org.springframework.stereotype.Repository

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@Repository
class OperatorRepositoryImpl(
    private val mapper: OperatorMapper
) : OperatorRepository {
    override fun find(email: String): Operator? {
        val selectStatement = SelectDSL.select(OperatorDynamicSqlSupport.Operator.allColumns())
            .from(OperatorDynamicSqlSupport.Operator)
            .where(OperatorDynamicSqlSupport.Operator.email, isEqualTo(email))
            .build()
            .render(MYBATIS3)

        val record = mapper.selectOne(selectStatement)
        return record?.let { toModel(it) }
    }

    override fun find(id: Int): Operator? {
        val record = mapper.selectByPrimaryKey(id)
        return record?.let { toModel(it) }
    }

    private fun toModel(record: OperatorRecord): Operator {
        return Operator(
            record.id!!,
            record.email!!,
            record.password!!,
            record.name!!,
            record.roleType!!
        )
    }
}
