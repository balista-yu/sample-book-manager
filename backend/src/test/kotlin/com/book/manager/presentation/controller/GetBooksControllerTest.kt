package com.book.manager.presentation.controller

import com.book.manager.domain.model.entity.Book
import com.book.manager.domain.model.id.BookId
import com.book.manager.presentation.caster.BookResponse
import com.book.manager.presentation.caster.GetBookListResponse
import com.book.manager.usecase.getBooks.GetBooksUseCase
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

internal class GetBooksControllerTest {
    private val getBooksUseCase = mock<GetBooksUseCase>()
    private val getBooksController = GetBooksController(getBooksUseCase)

    @Test
    fun testGetBooks() {
        val bookId = BookId("1")
        val book = Book(bookId, "title", "author", LocalDateTime.now(), null)
        val bookList = listOf(book)

        whenever(getBooksUseCase()).thenReturn(bookList)

        val expectedResponse = GetBookListResponse(listOf(BookResponse(bookId.value, "title", "author", false)))
        val expected = ObjectMapper().registerKotlinModule().writeValueAsString(expectedResponse)
        val mockMvc = MockMvcBuilders.standaloneSetup(getBooksController).build()
        val resultResponse = mockMvc.perform(get("/book/list"))
            .andExpect(status().isOk)
            .andReturn()
            .response
        val result = resultResponse.getContentAsString(StandardCharsets.UTF_8)

        Assertions.assertThat(expected).isEqualTo(result)
    }
}
