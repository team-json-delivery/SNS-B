package team_json_delivery.sns_b.global.config

import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.WebClient

class WebClientConfig {
    @Bean
    fun getWebclient(): WebClient =
        WebClient
            .builder()
            .build()
}
