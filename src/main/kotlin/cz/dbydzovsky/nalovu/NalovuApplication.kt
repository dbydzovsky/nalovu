package cz.dbydzovsky.nalovu

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NalovuApplication

fun main(args: Array<String>) {
	runApplication<NalovuApplication>(*args)
}
