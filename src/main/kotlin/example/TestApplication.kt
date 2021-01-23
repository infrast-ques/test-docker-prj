package example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["example.test"])
open class TestApplication

fun main(args: Array<String>) {
    runApplication<TestApplication>(*args)
}