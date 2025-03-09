package com.book.manager.presentation.controller

import com.book.manager.domain.model.id.BookId
import com.book.manager.usecase.deleteBook.DeleteBookUseCase
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("admin/book")
@CrossOrigin
class DeleteBookController(
    private val deleteBookUseCase: DeleteBookUseCase,
) {
    @DeleteMapping("/delete/{book_id}")
    operator fun invoke(@PathVariable("book_id") bookId: String) {
        deleteBookUseCase(BookId(bookId))
    }
}
