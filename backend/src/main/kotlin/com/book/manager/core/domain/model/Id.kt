package com.book.manager.core.domain.model

import java.io.Serializable

sealed class Id<T : Any>(open val value: T) : Serializable {
    data class Numeric(override val value: Int) : Id<Int>(value)
    data class Text(override val value: String) : Id<String>(value)

    override fun toString(): String = value.toString()
    companion object {
        private const val serialVersionUID: Long = 1L
    }
}
