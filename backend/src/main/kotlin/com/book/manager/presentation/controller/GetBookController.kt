package com.book.manager.presentation.controller

import com.book.manager.domain.model.id.BookId
import com.book.manager.presentation.caster.GetBookDetailResponse
import com.book.manager.usecase.getBook.GetBookUseCase
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("book")
@CrossOrigin
class GetBookController(
    private val getBookUseCase: GetBookUseCase
) {
    @GetMapping("/detail/{bookId}")
    operator fun invoke(@PathVariable("bookId")bookId: String): GetBookDetailResponse {
        val book = getBookUseCase(BookId(bookId))
        return GetBookDetailResponse(book)
    }
}
