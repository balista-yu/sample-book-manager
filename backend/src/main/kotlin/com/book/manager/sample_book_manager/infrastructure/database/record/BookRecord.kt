package com.book.manager.sample_book_manager.infrastructure.database.record

import java.time.LocalDateTime

data class BookRecord(
    var id: Int? = null,
    var title: String? = null,
    var author: String? = null,
    var releaseDate: LocalDateTime? = null
)
