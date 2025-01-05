package com.book.manager.sample_book_manager.infrastructure.database.repository

import com.book.manager.sample_book_manager.domain.model.Rental
import com.book.manager.sample_book_manager.domain.repository.RentalRepository
import com.book.manager.sample_book_manager.infrastructure.database.mapper.RentalMapper
import com.book.manager.sample_book_manager.infrastructure.database.mapper.deleteByPrimaryKey
import com.book.manager.sample_book_manager.infrastructure.database.mapper.insertOne
import com.book.manager.sample_book_manager.infrastructure.database.record.RentalRecord
import org.springframework.stereotype.Repository

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@Repository
class RentalRepositoryImpl(
    private val rentalMapper: RentalMapper
) : RentalRepository {
    override fun startRental(rental: Rental) {
        rentalMapper.insertOne(toRecord(rental))
    }

    override fun endRental(bookId: Int) {
        rentalMapper.deleteByPrimaryKey(bookId)
    }

    private fun toRecord(model: Rental): RentalRecord {
        return RentalRecord(
            model.bookId,
            model.userId,
            model.rentalDatetime,
            model.returnDeadline
        )
    }
}
