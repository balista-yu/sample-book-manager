package com.book.manager.core.domain.factory

import org.springframework.stereotype.Component
import ulid.ULID

@Component
class IdFactory {
    fun create(): String {
        return ULID.randomULID()
    }
}
