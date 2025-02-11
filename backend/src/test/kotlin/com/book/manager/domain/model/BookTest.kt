package com.book.manager.domain.model

import com.book.manager.domain.model.id.BookId
import com.book.manager.domain.model.id.OperatorId
import com.book.manager.domain.model.value.Rental
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

internal class BookTest {
    @Test
    fun `isRental when rental is null then return false`() {
        val book = Book(BookId("1"), "Kotlin", "taro", LocalDateTime.now(), null)
        Assertions.assertThat(book.isRental).isFalse()
    }

    @Test
    fun `isRental when rental is not null then return true`() {
        val book =
            Book(
                BookId("1"),
                "Kotlin",
                "taro",
                LocalDateTime.now(),
                Rental(OperatorId(1), LocalDateTime.now(), LocalDateTime.MAX)
            )
        Assertions.assertThat(book.isRental).isTrue()
    }
}
