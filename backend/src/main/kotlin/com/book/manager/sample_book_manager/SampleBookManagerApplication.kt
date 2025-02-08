package com.book.manager.sample_book_manager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class SampleBookManagerApplication

fun main(args: Array<String>) {
	runApplication<SampleBookManagerApplication>(*args)
}
