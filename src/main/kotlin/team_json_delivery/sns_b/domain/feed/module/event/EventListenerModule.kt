package team_json_delivery.sns_b.domain.feed.module.event

import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import team_json_delivery.sns_b.domain.feed.domain.Feed
import team_json_delivery.sns_b.domain.feed.domain.vo.UserID
import team_json_delivery.sns_b.domain.feed.module.rest.PostRestModule
import team_json_delivery.sns_b.domain.feed.service.CreateFeedService
import team_json_delivery.sns_b.domain.feed.service.DeleteFeedService
import team_json_delivery.sns_b.global.event.FollowEventDto
import team_json_delivery.sns_b.global.event.FollowEventType

@Component
class EventListenerModule(
    val restModule: PostRestModule,
    val deleteFeedService: DeleteFeedService,
    val createFeedService: CreateFeedService,
) {
    @Async
    @EventListener
    fun followEvent(message: FollowEventDto) {
        val follower = UserID(message.follower)
        val followee = UserID(message.followee)
        when (message.type) {
            FollowEventType.Follow ->
                createFeedService.createFeeds(createFeeds(follower = follower, followee = followee))
            FollowEventType.UnFollow ->
                deleteFeedService.deleteFeedsFor(
                    follower = follower,
                    followee = followee,
                )
        }
    }

    private fun createFeeds(
        follower: UserID,
        followee: UserID,
    ): List<Feed> =
        restModule.getPostIdFor(followee).map {
            Feed(follower = follower, followee = followee, post = it)
        }
}
