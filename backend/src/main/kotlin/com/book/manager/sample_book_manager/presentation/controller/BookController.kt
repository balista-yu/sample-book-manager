package com.book.manager.sample_book_manager.presentation.controller

import com.book.manager.sample_book_manager.application.service.BookService
import com.book.manager.sample_book_manager.presentation.form.BookInfo
import com.book.manager.sample_book_manager.presentation.form.GetBookDetailResponse
import com.book.manager.sample_book_manager.presentation.form.GetBookListResponse
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
    fun getDetail(bookId: Int): GetBookDetailResponse {
        val book = bookService.getDetail(bookId)
        return GetBookDetailResponse(book)
    }
}
