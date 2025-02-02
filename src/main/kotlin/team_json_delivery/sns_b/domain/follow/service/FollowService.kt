package team_json_delivery.sns_b.domain.follow.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team_json_delivery.sns_b.domain.follow.domain.vo.UserID

@Service
@Transactional
class FollowService {
    fun follow(
        follower: UserID,
        followee: UserID,
    ) {
    }

    fun unFollow(
        follower: UserID,
        followee: UserID,
    ) {
    }
}
