package com.book.manager.presentation.controller

import com.book.manager.domain.model.entity.Book
import com.book.manager.domain.model.id.BookId
import com.book.manager.presentation.caster.GetBookDetailResponse
import com.book.manager.usecase.getBook.GetBookUseCase
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
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

internal class GetBookControllerTest {
    private val getBookUseCase = mock<GetBookUseCase>()
    private val getBookController = GetBookController(getBookUseCase)

    @Test
    fun testGetBook() {
        val bookId = BookId("1")
        val book = Book(bookId, "title", "author", LocalDateTime.now(), null)

        whenever(getBookUseCase(bookId)).thenReturn(book)

        val expectedResponse = GetBookDetailResponse(book)
        val expected = ObjectMapper()
            .registerKotlinModule()
            .registerModule(JavaTimeModule())
            .writeValueAsString(expectedResponse)
        val mockMvc = MockMvcBuilders.standaloneSetup(getBookController).build()
        val resultResponse = mockMvc.perform(get("/book/detail/{bookId}", "1"))
            .andExpect(status().isOk)
            .andReturn()
            .response
        val result = resultResponse.getContentAsString(StandardCharsets.UTF_8)

        Assertions.assertThat(expected).isEqualTo(result)
    }
}
