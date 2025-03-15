package com.book.manager.usecase.registerBook

import com.book.manager.domain.model.id.BookId

data class RegisterBookOutput(
    val bookId: BookId,
)
