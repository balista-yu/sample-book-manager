package com.book.manager.presentation.controller

import com.book.manager.presentation.form.RentalStartRequest
import com.book.manager.usecase.security.BookManagerOperator
import com.book.manager.usecase.startRental.StartRentalInput
import com.book.manager.usecase.startRental.StartRentalUseCase
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("rental")
@CrossOrigin
class StartRentalController(
    private val startRentalUseCase: StartRentalUseCase,
) {
    @PostMapping("/start")
    operator fun invoke(@RequestBody request: RentalStartRequest) {
        val operator = SecurityContextHolder.getContext().authentication.principal as BookManagerOperator
        startRentalUseCase(StartRentalInput(request.bookId, operator.id))
    }
}
