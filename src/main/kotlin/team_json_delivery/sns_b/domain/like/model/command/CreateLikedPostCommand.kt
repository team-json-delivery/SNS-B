package team_json_delivery.sns_b.domain.like.model.command

data class CreateLikedPostCommand(
    val userId: Long,
    val postId: Long
)