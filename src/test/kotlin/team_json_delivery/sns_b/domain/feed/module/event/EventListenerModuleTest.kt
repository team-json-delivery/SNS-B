package team_json_delivery.sns_b.domain.feed.module.event

import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import team_json_delivery.sns_b.domain.feed.domain.Feed
import team_json_delivery.sns_b.domain.feed.domain.vo.PostID
import team_json_delivery.sns_b.domain.feed.domain.vo.UserID
import team_json_delivery.sns_b.domain.feed.module.rest.PostRestModule
import team_json_delivery.sns_b.domain.feed.service.CreateFeedService
import team_json_delivery.sns_b.domain.feed.service.DeleteFeedService
import team_json_delivery.sns_b.global.event.FollowEventDto
import team_json_delivery.sns_b.global.event.FollowEventType
import java.util.*

@Import(EventListenerModule::class, EventListenerModuleTestConfiguration::class)
class EventListenerModuleTest(
    private val eventPublisher: ApplicationEventPublisher,
    private val restModule: PostRestModule,
    private val deleteFeedService: DeleteFeedService,
    private val createFeedService: CreateFeedService,
) : BehaviorSpec({
        afterEach {
            clearAllMocks()
        }
        val follower = UserID("yoonhc@ncsoft.com")
        val followee = UserID("scpark88@ncsoft.com")

        given("follower 이벤트") {
            val dto =
                FollowEventDto(
                    follower = follower.value,
                    followee = followee.value,
                    type = FollowEventType.Follow,
                )
            val posts =
                listOf(
                    PostID(UUID.randomUUID().toString()),
                    PostID(UUID.randomUUID().toString()),
                    PostID(UUID.randomUUID().toString()),
                )
            val feeds =
                posts.map { post ->
                    Feed(
                        follower = follower,
                        followee = followee,
                        post = post,
                    )
                }
            `when`("호출") {
                every { restModule.getPostIdFor(followee) } returns posts
                every {
                    createFeedService.createFeeds(
                        match { actualFeeds ->
                            if (actualFeeds.size != feeds.size) return@match false

                            actualFeeds.zip(feeds).all { (actual, expected) ->
                                actual.followee == expected.followee &&
                                    actual.follower == expected.follower &&
                                    actual.post == expected.post
                            }
                        },
                    )
                } returns feeds
                eventPublisher.publishEvent(dto)
                then("각 이벤트 처리") {
                    verify { restModule.getPostIdFor(followee) }
                    verify {
                        createFeedService.createFeeds(
                            match { actualFeeds ->
                                if (actualFeeds.size != feeds.size) return@match false

                                actualFeeds.zip(feeds).all { (actual, expected) ->
                                    actual.followee == expected.followee &&
                                        actual.follower == expected.follower &&
                                        actual.post == expected.post
                                }
                            },
                        )
                    }
                }
            }
        }

        given("unFollower 이벤트") {
            val dto =
                FollowEventDto(
                    follower = follower.value,
                    followee = followee.value,
                    type = FollowEventType.UnFollow,
                )
            `when`("호출") {
                every { deleteFeedService.deleteFeedsFor(follower = follower, followee = followee) } returns Unit
                eventPublisher.publishEvent(dto)
                then("각 이벤트 처리") {
                    verify {
                        deleteFeedService.deleteFeedsFor(follower = follower, followee = followee)
                    }
                }
            }
        }
    })

@TestConfiguration
class EventListenerModuleTestConfiguration {
    @Bean
    fun restModule(): PostRestModule = mockk()

    @Bean
    fun deleteFeedService(): DeleteFeedService = mockk()

    @Bean
    fun createFeedService(): CreateFeedService = mockk()
}
