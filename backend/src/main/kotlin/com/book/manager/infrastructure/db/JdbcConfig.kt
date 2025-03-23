package com.book.manager.infrastructure.db

import com.atomikos.jdbc.AtomikosDataSourceBean
import org.postgresql.xa.PGXADataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class JdbcConfig {
    @Bean("firstDataSource")
    @ConfigurationProperties("spring.datasource.first")
    fun createFirstDataSource(): DataSource {
        val xaDataSource = PGXADataSource()
        xaDataSource.setUrl("jdbc:postgresql://postgres:5432/sample_book_manager")
        xaDataSource.user = "test"
        xaDataSource.password = "test"

        val dataSource = AtomikosDataSourceBean()
        dataSource.uniqueResourceName = "firstDataSource"
        dataSource.xaDataSource = xaDataSource
        return dataSource
    }

    @Bean("secondDataSource")
    @ConfigurationProperties("spring.datasource.second")
    fun createSecondDataSource(): DataSource {
        val xaDataSource = PGXADataSource()
        xaDataSource.setUrl("jdbc:postgresql://postgres:5432/sample_book_manager_other")
        xaDataSource.user = "test"
        xaDataSource.password = "test"

        val dataSource = AtomikosDataSourceBean()
        dataSource.uniqueResourceName = "secondDataSource"
        dataSource.xaDataSource = xaDataSource
        return dataSource
    }
}
