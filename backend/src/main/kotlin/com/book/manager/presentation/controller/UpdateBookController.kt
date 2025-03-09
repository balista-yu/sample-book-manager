package com.book.manager.presentation.controller

import com.book.manager.domain.model.id.BookId
import com.book.manager.presentation.form.UpdateBookRequest
import com.book.manager.usecase.updateBook.UpdateBookUseCase
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("admin/book")
@CrossOrigin
class UpdateBookController(
    private val updateBookUseCase: UpdateBookUseCase,
) {
    @PutMapping("/update")
    operator fun invoke(@RequestBody request: UpdateBookRequest) {
        updateBookUseCase(
            BookId(request.id),
            request.title,
            request.author,
            request.releaseDate
        )
    }
}
