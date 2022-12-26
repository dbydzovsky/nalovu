package cz.dbydzovsky.nalovu

import SecurityConfig
import cz.dbydzovsky.nalovu.security.WebConfig
import cz.dbydzovsky.nalovu.sockjs.WebSocketConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.security.core.context.SecurityContext

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan(basePackages = ["cz.dbydzovsky.nalovu"])
@Import(value=[
	SecurityConfig::class,
	WebConfig::class,
	WebSocketConfig::class
])
class NalovuApplication

fun main(args: Array<String>) {
	runApplication<NalovuApplication>(*args)
}
