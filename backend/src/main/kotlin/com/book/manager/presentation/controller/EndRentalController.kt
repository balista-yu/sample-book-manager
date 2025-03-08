package com.book.manager.presentation.controller

import com.book.manager.domain.model.id.BookId
import com.book.manager.usecase.endRental.EndRentalUseCase
import com.book.manager.usecase.security.BookManagerOperatorDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("rental")
@CrossOrigin
class EndRentalController(
    private val endRentalUseCase: EndRentalUseCase
) {
    @DeleteMapping("/end/{book_id}")
    operator fun invoke(@PathVariable("book_id") bookId: String) {
        val operator = SecurityContextHolder.getContext().authentication.principal as BookManagerOperatorDetails
        endRentalUseCase(BookId(bookId), operator.id)
    }
}
