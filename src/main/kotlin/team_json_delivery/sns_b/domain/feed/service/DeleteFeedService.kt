package team_json_delivery.sns_b.domain.feed.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team_json_delivery.sns_b.domain.feed.domain.vo.UserID
import team_json_delivery.sns_b.domain.feed.repository.FeedRepository

@Service
@Transactional
class DeleteFeedService(
    private val repository: FeedRepository,
) {
    fun deleteFeedsFor(
        follower: UserID,
        followee: UserID,
    ) = repository.deleteAllByFollowerAndFollowee(follower = follower.value, followee = followee.value)
}
