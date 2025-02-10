package team_json_delivery.sns_b.domain.follow.module.event

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import team_json_delivery.sns_b.domain.follow.domain.Follow
import team_json_delivery.sns_b.global.event.FollowEventDto
import team_json_delivery.sns_b.global.event.FollowEventType

@Component
class EventPublisherModule(
    val publisher: ApplicationEventPublisher,
) {
    fun followEventsPublish(follow: Follow) {
        publisher.publishEvent(
            FollowEventDto(follower = follow.follower.value, followee = follow.followee.value, type = FollowEventType.Follow),
        )
    }

    fun unFollowEventsPublish(follow: Follow) {
        publisher.publishEvent(
            FollowEventDto(follower = follow.follower.value, followee = follow.followee.value, type = FollowEventType.UnFollow),
        )
    }
}
