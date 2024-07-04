package de.comsystoreply.gearbox.bff.adapter.web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/test")
class TestController {

    @GetMapping("/message")
    fun message(): String = "Hello World"
}