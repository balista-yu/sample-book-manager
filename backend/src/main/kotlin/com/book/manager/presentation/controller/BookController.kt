package com.book.manager.presentation.controller

import com.book.manager.domain.model.id.BookId
import com.book.manager.presentation.caster.BookInfo
import com.book.manager.presentation.caster.GetBookDetailResponse
import com.book.manager.presentation.caster.GetBookListResponse
import com.book.manager.usecase.BookService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("book")
@CrossOrigin
class BookController(
    private val bookService: BookService
) {
    @GetMapping("/list")
    fun getList(): GetBookListResponse {
        val bookList = bookService.getList().map {
            BookInfo(it)
        }
        return GetBookListResponse(bookList)
    }

    @GetMapping("/detail/{bookId}")
    fun getDetail(bookId: String): GetBookDetailResponse {
        val book = bookService.getDetail(BookId(bookId))
        return GetBookDetailResponse(book)
    }
}
