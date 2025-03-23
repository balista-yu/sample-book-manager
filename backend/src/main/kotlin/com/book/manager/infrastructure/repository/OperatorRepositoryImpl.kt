package com.book.manager.infrastructure.repository

import com.book.manager.domain.criteria.OperatorCriteria
import com.book.manager.domain.model.entity.Operator
import com.book.manager.domain.repository.OperatorRepository
import com.book.manager.infrastructure.hydrator.OperatorHydrator
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import javax.sql.DataSource

@Repository
class OperatorRepositoryImpl(
    private val operatorHydrator: OperatorHydrator,
    @Qualifier("firstDataSource")
    private val firstDataSource: DataSource,
) : OperatorRepository {
    override fun find(criteria: OperatorCriteria): Operator? {
        val firstDataJdbc = NamedParameterJdbcTemplate(firstDataSource)
        val sql = StringBuilder("SELECT id, email, password, name, role_type FROM operator")
        val params = mutableMapOf<String, Any>()

        if (criteria.id != null) {
            sql.append(" WHERE id = :id")
            params["id"] = criteria.id.value
        }

        if (criteria.email != null) {
            if (params.isNotEmpty()) {
                sql.append(" AND")
            } else {
                sql.append(" WHERE")
            }
            sql.append(" email = :email")
            params["email"] = criteria.email
        }

        return firstDataJdbc.query(sql.toString(), params) { rs, _ -> operatorHydrator.hydrate(rs) }.firstOrNull()
    }
}
