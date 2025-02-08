package com.book.manager.sample_book_manager.core.enum

enum class RoleTypes(val value: String) {
    ADMIN("ADMIN"),
    GENERAL("GENERAL");

    companion object {
        fun fromValue(value: String): RoleTypes {
            return RoleTypes.entries.firstOrNull { it.value == value }
                ?: throw IllegalArgumentException("Invalid role type: $value")
        }
    }
}
