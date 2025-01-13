package com.book.manager.infrastructure.database.record

import com.book.manager.domain.enum.RoleType

data class OperatorRecord(
    var id: Int? = null,
    var email: String? = null,
    var password: String? = null,
    var name: String? = null,
    var roleType: RoleType? = null
)
