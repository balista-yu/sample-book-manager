package com.book.manager.presentation.controller

import com.book.manager.usecase.updateBook.UpdateBookInput
import com.book.manager.usecase.updateBook.UpdateBookUseCase
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

internal class UpdateBookControllerTest {
    private val updateBookUseCase = mock<UpdateBookUseCase>()
    private val updateBookController = UpdateBookController(updateBookUseCase)

    @Test
    fun testUpdateBook() {
        doNothing().whenever(updateBookUseCase).invoke(UpdateBookInput("1000", "title", "author", null))

        val params = mapOf(
            "id" to "1000",
            "title" to "title",
            "author" to "author",
            "releaseDate" to null,
        )
        val mockMvc = MockMvcBuilders.standaloneSetup(updateBookController).build()
        mockMvc.perform(
            put("/admin/book/update").contentType(MediaType.APPLICATION_JSON).content(
                ObjectMapper().writeValueAsString(params),
            ),
        )
            .andExpect(status().isOk)
    }
}
