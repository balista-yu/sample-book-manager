package com.book.manager.presentation.controller

import com.book.manager.usecase.deleteBook.DeleteBookInput
import com.book.manager.usecase.deleteBook.DeleteBookUseCase
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

internal class DeleteBookControllerTest {
    private val deleteBookUseCase = mock<DeleteBookUseCase>()
    private val deleteBookController = DeleteBookController(deleteBookUseCase)

    @Test
    fun testDeleteBook() {
        doNothing().whenever(deleteBookUseCase).invoke(DeleteBookInput("1"))

        val mockMvc = MockMvcBuilders.standaloneSetup(deleteBookController).build()
        mockMvc.perform(delete("/admin/book/delete/{bookId}", "1"))
            .andExpect(status().isOk)
    }
}
