package com.techeasy.contas.receber

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect


@SpringBootApplication
@EnableTransactionManagement
class Application {


	@Bean
	fun java8TimeDialect(): Java8TimeDialect? {
		return Java8TimeDialect()
	}

}

fun main(args: Array<String>) {

	runApplication<Application>(*args)
}


