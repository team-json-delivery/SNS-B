package team_json_delivery.sns_b.domain.follow.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team_json_delivery.sns_b.domain.follow.domain.vo.UserID
import team_json_delivery.sns_b.domain.follow.exception.NotFoundFollowException
import team_json_delivery.sns_b.domain.follow.module.event.EventPublisherModule
import team_json_delivery.sns_b.domain.follow.repository.FollowRepository

@Service
@Transactional
class UnFollowService(
    val eventPublisherModule: EventPublisherModule,
    val repository: FollowRepository,
) {
    @Throws(NotFoundFollowException::class)
    fun unFollow(
        follower: UserID,
        followee: UserID,
    ) {
        val follow =
            repository.findFirstByFollowerAndFollowee(follower = follower.value, followee = followee.value)
                ?: throw NotFoundFollowException()
        repository.deleteById(follow.id)
        eventPublisherModule.unFollowEventsPublish(follow = follow)
    }
}
