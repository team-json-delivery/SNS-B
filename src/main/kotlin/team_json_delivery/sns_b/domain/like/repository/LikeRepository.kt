package team_json_delivery.sns_b.domain.like.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import team_json_delivery.sns_b.domain.like.domain.Like
import team_json_delivery.sns_b.domain.user.domain.User

@Repository
interface LikeRepository : JpaRepository<Like, Long> {
    fun findByUserId(userId: Long): List<Like>
    fun findByUserIdAndPostId(userId: Long, postId: Long): Like?
}