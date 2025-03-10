package com.book.manager.presentation.controller

import com.book.manager.presentation.caster.BookResponse
import com.book.manager.presentation.caster.GetBookListResponse
import com.book.manager.usecase.getBooks.GetBooksUseCase
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("book")
@CrossOrigin
class GetBooksController(
    private val getBooksUseCase: GetBooksUseCase,
) {
    @GetMapping("/list")
    operator fun invoke(): GetBookListResponse {
        val bookList = getBooksUseCase().map {
            BookResponse(it)
        }
        return GetBookListResponse(bookList)
    }
}
