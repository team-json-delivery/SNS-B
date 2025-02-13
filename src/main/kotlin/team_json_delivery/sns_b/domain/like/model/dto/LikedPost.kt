package team_json_delivery.sns_b.domain.like.model.dto

import java.time.LocalDateTime

data class LikedPost(
    val userId: Long,
    val postId: Long,
    val createdAt: LocalDateTime
)