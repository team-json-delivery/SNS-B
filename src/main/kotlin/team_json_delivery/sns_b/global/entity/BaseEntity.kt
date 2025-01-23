package team_json_delivery.sns_b.global.entity

import java.time.LocalDateTime

open class BaseEntity {
    val createdAt: LocalDateTime = LocalDateTime.now()
    val updatedAt: LocalDateTime = LocalDateTime.now()
}
