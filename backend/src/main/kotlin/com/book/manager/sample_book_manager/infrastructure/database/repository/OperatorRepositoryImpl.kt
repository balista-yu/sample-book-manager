package com.book.manager.sample_book_manager.infrastructure.database.repository

import com.book.manager.sample_book_manager.domain.model.Operator
import com.book.manager.sample_book_manager.domain.repository.OperatorRepository
import com.book.manager.sample_book_manager.infrastructure.database.hydrator.OperatorHydrator
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class OperatorRepositoryImpl(
    private val jdbcTemplate: JdbcTemplate,
    private val operatorHydrator: OperatorHydrator
) : OperatorRepository {
    override fun find(email: String): Operator? {
        val sql = "SELECT id, email, password, name, role_type FROM operator WHERE email = ?;"
        return jdbcTemplate.query(sql, { rs, _ -> operatorHydrator.hydrate(rs)}, email).firstOrNull()
    }

    override fun find(id: Int): Operator? {
        val sql = "SELECT id, email, password, name, role_type FROM operator WHERE id = ?;"
        return jdbcTemplate.query(sql, { rs, _ -> operatorHydrator.hydrate(rs)}, id).firstOrNull()
    }
}
