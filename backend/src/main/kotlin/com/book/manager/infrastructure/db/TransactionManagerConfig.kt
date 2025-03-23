package com.book.manager.infrastructure.db

import com.atomikos.icatch.jta.UserTransactionImp
import com.atomikos.icatch.jta.UserTransactionManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.jta.JtaTransactionManager

@Configuration
class TransactionManagerConfig {
    @Bean
    fun userTransaction(): UserTransactionImp = UserTransactionImp()

    @Bean
    fun userTransactionManager(): UserTransactionManager {
        val userTransactionManager = UserTransactionManager()
        userTransactionManager.init()
        return userTransactionManager
    }

    @Bean
    fun transactionManager(): PlatformTransactionManager =
        JtaTransactionManager(userTransaction(), userTransactionManager())
}
