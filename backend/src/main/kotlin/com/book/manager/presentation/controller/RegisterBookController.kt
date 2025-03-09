package com.book.manager.presentation.controller

import com.book.manager.core.domain.factory.IdFactory
import com.book.manager.domain.model.entity.Book
import com.book.manager.domain.model.id.BookId
import com.book.manager.presentation.form.RegisterBookRequest
import com.book.manager.usecase.registerBook.RegisterBookUseCase
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("admin/book")
@CrossOrigin
class RegisterBookController(
    private val registerBookUseCase: RegisterBookUseCase,
    private val idFactory: IdFactory,
) {
    @PostMapping("/register")
    operator fun invoke(@RequestBody request: RegisterBookRequest) {
        registerBookUseCase(
            Book(
                id = BookId(idFactory.create()),
                title = request.title,
                author = request.author,
                releaseDate = request.releaseDate,
                rental = null,
            )
        )
    }
}
