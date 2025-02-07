package team_json_delivery.sns_b

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.extensions.spring.SpringTestExtension
import io.kotest.extensions.spring.SpringTestLifecycleMode

class SnsBProjectConfig {
    object ProjectConfig : AbstractProjectConfig() {
        override fun extensions() = listOf(SpringTestExtension(SpringTestLifecycleMode.Root))
    }
}
