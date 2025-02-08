package com.book.manager.sample_book_manager.domain.model

data class Operator(
    val id: Int,
    val email: String,
    val password: String,
    val name: String,
    val roleType: RoleType
)
