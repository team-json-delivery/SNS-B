package team_json_delivery.sns_b.global.entity

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import java.time.LocalDateTime

@MappedSuperclass
class BaseEntity {
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime = LocalDateTime.now()
}
