package com.book.manager.sample_book_manager.infrastructure.database.repository

import com.book.manager.sample_book_manager.domain.model.User
import com.book.manager.sample_book_manager.domain.repository.UserRepository
import com.book.manager.sample_book_manager.infrastructure.database.mapper.UserDynamicSqlSupport
import com.book.manager.sample_book_manager.infrastructure.database.mapper.UserMapper
import com.book.manager.sample_book_manager.infrastructure.database.mapper.selectByPrimaryKey
import com.book.manager.sample_book_manager.infrastructure.database.record.UserRecord
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.render.RenderingStrategies.MYBATIS3
import org.mybatis.dynamic.sql.select.SelectDSL
import org.springframework.stereotype.Repository

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@Repository
class UserRepositoryImpl(
    private val mapper: UserMapper
) : UserRepository {
    override fun find(email: String): User? {
        val selectStatement = SelectDSL.select(UserDynamicSqlSupport.User.allColumns())
            .from(UserDynamicSqlSupport.User)
            .where(UserDynamicSqlSupport.User.email, isEqualTo(email))
            .build()
            .render(MYBATIS3)

        val record = mapper.selectOne(selectStatement)
        return record?.let { toModel(it) }
    }

    override fun find(id: Int): User? {
        val record = mapper.selectByPrimaryKey(id)
        return record?.let { toModel(it) }
    }

    private fun toModel(record: UserRecord): User {
        return User(
            record.id!!,
            record.email!!,
            record.password!!,
            record.name!!,
            record.roleType!!
        )
    }
}
