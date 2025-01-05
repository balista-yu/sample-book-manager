package com.book.manager.sample_book_manager.domain.model

import com.book.manager.sample_book_manager.domain.enum.RoleType

data class User(
    val id: Int,
    val email: String,
    val password: String,
    val name: String,
    val roleType: RoleType
)
