package team_json_delivery.sns_b

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.extensions.spring.SpringExtension

class SnsBProjectConfig {
    object ProjectConfig : AbstractProjectConfig() {
        override fun extensions() = listOf(SpringExtension)
    }
}
