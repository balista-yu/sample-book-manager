package com.book.manager.domain.repository

import com.book.manager.domain.criteria.OperatorCriteria
import com.book.manager.domain.model.entity.Operator

interface OperatorRepository {
    fun find(criteria: OperatorCriteria): Operator?
}
