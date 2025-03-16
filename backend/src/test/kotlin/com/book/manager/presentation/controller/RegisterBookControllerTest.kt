package com.book.manager.presentation.controller

import com.book.manager.domain.model.id.BookId
import com.book.manager.usecase.registerBook.RegisterBookInput
import com.book.manager.usecase.registerBook.RegisterBookOutput
import com.book.manager.usecase.registerBook.RegisterBookUseCase
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.nio.charset.StandardCharsets
import java.time.LocalDateTime

internal class RegisterBookControllerTest {
    private val registerBookUseCase = mock<RegisterBookUseCase>()
    private val registerBookController = RegisterBookController(registerBookUseCase)

    @Test
    fun testRegisterBook() {
        val bookId = BookId("1")
        val releaseDate = LocalDateTime.now()

        whenever(
            registerBookUseCase(
                RegisterBookInput(
                    "title",
                    "author",
                    releaseDate,
                ),
            ),
        ).thenReturn(RegisterBookOutput(bookId))

        val params = mapOf(
            "title" to "title",
            "author" to "author",
            "releaseDate" to releaseDate.toString(),
        )
        val expected = ObjectMapper()
            .registerKotlinModule()
            .registerModule(JavaTimeModule())
            .writeValueAsString(bookId)
        val mockMvc = MockMvcBuilders.standaloneSetup(registerBookController).build()
        val resultResponse = mockMvc.perform(
            post("/admin/book/register").contentType(MediaType.APPLICATION_JSON).content(
                ObjectMapper().writeValueAsString(params),
            ),
        )
            .andExpect(status().isOk)
            .andReturn()
            .response
        val result = resultResponse.getContentAsString(StandardCharsets.UTF_8)

        Assertions.assertThat(expected).isEqualTo(result)
    }
}
