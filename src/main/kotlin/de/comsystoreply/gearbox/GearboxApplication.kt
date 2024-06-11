package de.comsystoreply.gearbox

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GearboxApplication

fun main(args: Array<String>) {
    runApplication<GearboxApplication>(*args)
}
