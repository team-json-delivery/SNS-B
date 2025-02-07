package team_json_delivery.sns_b.domain.follow.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team_json_delivery.sns_b.domain.follow.domain.vo.UserID
import team_json_delivery.sns_b.domain.follow.repository.FollowRepository

@Service
@Transactional
class UnFollowService(
    val repository: FollowRepository,
) {
    fun unFollow(
        follower: UserID,
        followee: UserID,
    ) {
    }
}
