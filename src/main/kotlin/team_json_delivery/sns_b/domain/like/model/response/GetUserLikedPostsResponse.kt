package team_json_delivery.sns_b.domain.like.model.response

import team_json_delivery.sns_b.domain.like.domain.Like
import team_json_delivery.sns_b.domain.like.model.dto.LikedPost
import java.time.LocalDateTime

class GetUserLikedPostsResponse(
    val likes: List<LikedPost>?
) {
    companion object {
        fun from(likes: List<Like>?): GetUserLikedPostsResponse {
            return GetUserLikedPostsResponse(
                likes = likes?.map {
                    LikedPost(
                        userId = it.userId,
                        postId = it.postId,
                        createdAt = it.createdAt
                    )
                }
            )
        }
    }
}