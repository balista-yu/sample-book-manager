package com.book.manager.sample_book_manager.presentation.controller

import com.book.manager.sample_book_manager.application.service.RentalService
import com.book.manager.sample_book_manager.application.service.security.BookManagerOperatorDetails
import com.book.manager.sample_book_manager.presentation.form.RentalStartRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("rental")
@CrossOrigin
class RentalController(
    private val rentalService: RentalService
) {
    @PostMapping("/start")
    fun startRental(@RequestBody request: RentalStartRequest) {
        val operator = SecurityContextHolder.getContext().authentication.principal as BookManagerOperatorDetails
        rentalService.startRental(request.bookId, operator.id)
    }

    @DeleteMapping("/end/{book_id}")
    fun endRental(@PathVariable("book_id") bookId: Int) {
        val operator = SecurityContextHolder.getContext().authentication.principal as BookManagerOperatorDetails
        rentalService.endRental(bookId, operator.id)
    }
}
