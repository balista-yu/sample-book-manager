package com.book.manager.application.service

import com.book.manager.domain.model.entity.Operator
import com.book.manager.domain.repository.OperatorRepository
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val operatorRepository: OperatorRepository
) {
    fun findOperator(email: String): Operator? {
        return operatorRepository.find(email)
    }
}
