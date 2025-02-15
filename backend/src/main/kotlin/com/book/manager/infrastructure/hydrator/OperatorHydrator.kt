package com.book.manager.infrastructure.hydrator

import com.book.manager.core.enum.RoleTypes
import com.book.manager.domain.model.entity.Operator
import com.book.manager.domain.model.id.OperatorId
import com.book.manager.domain.model.value.RoleType
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class OperatorHydrator {
    fun hydrate(rs: ResultSet): Operator {
        return Operator(
            id = OperatorId(rs.getInt("id")),
            email = rs.getString("email"),
            password = rs.getString("password"),
            name = rs.getString("name"),
            roleType = RoleType(RoleTypes.fromValue(rs.getString("role_type"))),
        )
    }
}
