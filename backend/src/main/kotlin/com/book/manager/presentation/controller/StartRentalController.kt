package com.book.manager.presentation.controller

import com.book.manager.domain.model.id.BookId
import com.book.manager.presentation.form.RentalStartRequest
import com.book.manager.usecase.security.BookManagerOperatorDetails
import com.book.manager.usecase.start.rental.StartRentalUseCase
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
    private val startRentalUseCase: StartRentalUseCase
) {
    @PostMapping("/start")
    operator fun invoke(@RequestBody request: RentalStartRequest) {
        val operator = SecurityContextHolder.getContext().authentication.principal as BookManagerOperatorDetails
        startRentalUseCase(BookId(request.bookId), operator.id)
    }
}
