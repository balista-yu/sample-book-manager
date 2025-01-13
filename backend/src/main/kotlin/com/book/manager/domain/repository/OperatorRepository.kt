package com.book.manager.domain.repository

import com.book.manager.domain.model.Operator

interface OperatorRepository {
    fun find(email: String): Operator?
    fun find(id: Int): Operator?
}
