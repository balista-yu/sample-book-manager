package com.book.manager.usecase.registerBook

import com.book.manager.core.domain.factory.IdFactory
import com.book.manager.domain.model.entity.Book
import com.book.manager.domain.model.id.BookId
import com.book.manager.domain.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RegisterBookUseCase(
    private val bookRepository: BookRepository,
    private val idFactory: IdFactory,
) {
    @Transactional
    operator fun invoke(registerBookInput: RegisterBookInput): RegisterBookOutput {
        val book = Book(
            id = BookId(idFactory.create()),
            title = registerBookInput.title,
            author = registerBookInput.author,
            releaseDate = registerBookInput.releaseDate,
            rental = null,
        )
        bookRepository.register(book)

        return RegisterBookOutput(book.id)
    }
}
