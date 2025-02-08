package com.book.manager.sample_book_manager.domain.model

import com.book.manager.sample_book_manager.core.enum.RoleTypes
import java.io.Serializable

data class RoleType(
    val value: RoleTypes,
) : Serializable
