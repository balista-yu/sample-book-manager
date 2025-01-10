package com.book.manager.sample_book_manager.application.service

import com.book.manager.sample_book_manager.domain.model.Operator
import com.book.manager.sample_book_manager.domain.repository.OperatorRepository
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val operatorRepository: OperatorRepository
) {
    fun findOperator(email: String): Operator? {
        return operatorRepository.find(email)
    }
}
