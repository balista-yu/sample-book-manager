package com.book.manager.domain.criteria

import com.book.manager.domain.model.id.OperatorId

data class OperatorCriteria(
    val id: OperatorId? = null,
    val email: String? = null,
)
