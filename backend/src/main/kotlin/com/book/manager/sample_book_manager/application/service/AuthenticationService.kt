package com.book.manager.sample_book_manager.application.service

import com.book.manager.sample_book_manager.domain.model.User
import com.book.manager.sample_book_manager.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val userRepository: UserRepository
) {
    fun findUser(email: String): User? {
        return userRepository.find(email)
    }
}
