package team_json_delivery.sns_b.domain.like.service

import org.springframework.stereotype.Service
import team_json_delivery.sns_b.domain.like.domain.Like
import team_json_delivery.sns_b.domain.like.repository.LikeRepository

@Service
class LikeQueryService(
    private val likeRepository: LikeRepository
) {
    fun getLikedPosts(userId: Long): List<Like> {
        return likeRepository.findByUserId(userId)
    }

    fun hasLike(userId: Long, postId: Long): Boolean {
        return likeRepository.findByUserIdAndPostId(userId, postId) != null
    }
}