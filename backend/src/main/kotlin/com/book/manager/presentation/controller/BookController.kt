package com.book.manager.presentation.controller

import com.book.manager.usecase.BookService
import com.book.manager.domain.model.id.BookId
import com.book.manager.presentation.form.BookInfo
import com.book.manager.presentation.form.GetBookDetailResponse
import com.book.manager.presentation.form.GetBookListResponse
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
