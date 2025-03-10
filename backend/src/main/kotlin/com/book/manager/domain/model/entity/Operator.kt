package com.book.manager.domain.model.entity

import com.book.manager.domain.model.id.OperatorId
import com.book.manager.domain.model.value.RoleType

data class Operator(
    val id: OperatorId,
    val email: String,
    val password: String,
    val name: String,
    val roleType: RoleType,
)
