package com.book.manager.presentation.controller

import com.book.manager.core.domain.model.Id
import com.book.manager.core.enum.RoleTypes
import com.book.manager.domain.model.value.RoleType
import com.book.manager.usecase.security.BookManagerOperator
import com.book.manager.usecase.startRental.StartRentalInput
import com.book.manager.usecase.startRental.StartRentalUseCase
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

internal class StartRentalControllerTest {
    private val startRentalUseCase = mock<StartRentalUseCase>()
    private val startRentalController = StartRentalController(startRentalUseCase)
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
    fun testStartRental() {
        doNothing().whenever(startRentalUseCase).invoke(StartRentalInput("1", mockOperator.id))
        val params = mapOf("bookId" to "1")

        val mockMvc = MockMvcBuilders.standaloneSetup(startRentalController).build()
        mockMvc.perform(
            post("/rental/start").contentType(MediaType.APPLICATION_JSON).content(
                ObjectMapper().writeValueAsString(params),
            ),
        )
            .andExpect(status().isOk)
    }
}
