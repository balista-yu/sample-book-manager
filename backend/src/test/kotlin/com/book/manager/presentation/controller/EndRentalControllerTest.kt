package com.book.manager.presentation.controller

import com.book.manager.core.domain.model.Id
import com.book.manager.core.enum.RoleTypes
import com.book.manager.domain.model.value.RoleType
import com.book.manager.usecase.endRental.EndRentalInput
import com.book.manager.usecase.endRental.EndRentalUseCase
import com.book.manager.usecase.security.BookManagerOperator
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

internal class EndRentalControllerTest {
    private val endRentalUseCase = mock<EndRentalUseCase>()
    private val endRentalController = EndRentalController(endRentalUseCase)
    private val mockSecurityContext: SecurityContext = mock()
    private val mockAuthentication: Authentication = mock()
    private val mockOperator =
        BookManagerOperator(Id.Numeric(100), "dummy@example.com", "test", RoleType(RoleTypes.GENERAL))

    @BeforeEach
    fun setUp() {
        whenever(mockSecurityContext.authentication).thenReturn(mockAuthentication)
        whenever(mockAuthentication.principal).thenReturn(mockOperator)
        SecurityContextHolder.setContext(mockSecurityContext)
    }

    @AfterEach
    fun tearDown() {
        SecurityContextHolder.clearContext()
    }

    @Test
    fun testEndRental() {
        doNothing().whenever(endRentalUseCase).invoke(EndRentalInput("1", mockOperator.id))

        val mockMvc = MockMvcBuilders.standaloneSetup(endRentalController).build()
        mockMvc.perform(delete("/rental/end/{book_id}", "1"))
            .andExpect(status().isOk)
    }
}
