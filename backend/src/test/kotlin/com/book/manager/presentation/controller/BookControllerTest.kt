package com.book.manager.presentation.controller

import com.book.manager.usecase.BookService
import com.book.manager.domain.model.entity.Book
import com.book.manager.domain.model.id.BookId
import com.book.manager.presentation.form.BookInfo
import com.book.manager.presentation.form.GetBookListResponse
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.nio.charset.StandardCharsets
import java.time.LocalDateTime

internal class BookControllerTest {
    private val bookService = mock<BookService>()
    private val bookController = BookController(bookService)

    @Test
    fun `getList is success`() {
        val bookId = BookId("1")
        val book = Book(bookId, "title", "author", LocalDateTime.now(), null)
        val bookList = listOf(book)

        whenever(bookService.getList()).thenReturn(bookList)

        val expectedResponse = GetBookListResponse(listOf(BookInfo(bookId.value, "title", "author", false)))
        val expected = ObjectMapper().registerKotlinModule().writeValueAsString(expectedResponse)
        val mockMvc = MockMvcBuilders.standaloneSetup(bookController).build()
        val resultResponse = mockMvc.perform(get("/book/list"))
            .andExpect(status().isOk)
            .andReturn()
            .response
        val result = resultResponse.getContentAsString(StandardCharsets.UTF_8)

        Assertions.assertThat(expected).isEqualTo(result)
    }
}
