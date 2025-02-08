package com.book.manager.sample_book_manager.infrastructure.database.hydrator

import com.book.manager.sample_book_manager.core.enum.RoleTypes
import com.book.manager.sample_book_manager.domain.model.Operator
import com.book.manager.sample_book_manager.domain.model.RoleType
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class OperatorHydrator {
    fun hydrate(rs: ResultSet): Operator {
        return Operator(
            id = rs.getInt("id"),
            email = rs.getString("email"),
            password = rs.getString("password"),
            name = rs.getString("name"),
            roleType = RoleType(RoleTypes.fromValue(rs.getString("role_type")))
        )
    }
}
