package com.book.manager.domain.model.value

import com.book.manager.core.enum.RoleTypes
import java.io.Serializable

data class RoleType(
    val value: RoleTypes,
) : Serializable {
    companion object {
        private const val serialVersionUID: Long = 1L
    }
}
