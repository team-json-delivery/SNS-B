package team_json_delivery.sns_b.domain.like.model.command

data class DeleteLikedPostCommand(
    val userId: Long,
    val postId: Long
)