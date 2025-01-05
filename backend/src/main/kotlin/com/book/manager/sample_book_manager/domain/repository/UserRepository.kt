package com.book.manager.sample_book_manager.domain.repository

import com.book.manager.sample_book_manager.domain.model.User

interface UserRepository {
    fun find(email: String): User?
    fun find(id: Int): User?
}
