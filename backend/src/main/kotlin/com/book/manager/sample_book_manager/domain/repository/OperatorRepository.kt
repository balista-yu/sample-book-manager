package com.book.manager.sample_book_manager.domain.repository

import com.book.manager.sample_book_manager.domain.model.Operator

interface OperatorRepository {
    fun find(email: String): Operator?
    fun find(id: Int): Operator?
}
