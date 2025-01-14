package team_json_delivery.sns_b.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team_json_delivery.sns_b.service.Service

@RestController
class Controller(
    val service: Service,
) {
    @RequestMapping("/")
    fun index(): String = "hello world"
}
