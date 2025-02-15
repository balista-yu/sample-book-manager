package com.book.manager.domain.repository

import com.book.manager.domain.model.entity.Operator
import com.book.manager.domain.model.id.OperatorId

interface OperatorRepository {
    fun find(email: String): Operator?
    fun find(id: OperatorId): Operator?
}
