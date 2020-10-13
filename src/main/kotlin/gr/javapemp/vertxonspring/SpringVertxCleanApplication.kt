package gr.javapemp.vertxonspring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringVertxCleanApplication

fun main(args: Array<String>) {
    runApplication<SpringVertxCleanApplication>(*args)
}
