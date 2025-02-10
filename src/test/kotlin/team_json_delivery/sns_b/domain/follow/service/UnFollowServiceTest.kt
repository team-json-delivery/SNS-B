package team_json_delivery.sns_b.domain.follow.service

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import team_json_delivery.sns_b.domain.follow.domain.Follow
import team_json_delivery.sns_b.domain.follow.domain.vo.UserID
import team_json_delivery.sns_b.domain.follow.exception.NotFoundFollowException
import team_json_delivery.sns_b.domain.follow.module.event.EventPublisherModule
import team_json_delivery.sns_b.domain.follow.repository.FollowRepository

@Import(UnFollowService::class, UnFollowServiceTestConfiguration::class)
@DataJpaTest
class UnFollowServiceTest(
    val sut: UnFollowService,
    val repository: FollowRepository,
    val eventPublisherModule: EventPublisherModule,
) : BehaviorSpec({
        given("데이터 unfollow") {
            val follower = UserID("yoonhc@ncsoft.com")
            val followee = UserID("scpark88@ncsoft.com")
            repository.save(Follow(follower = follower, followee = followee))
            `when`("호출") {
                every {
                    eventPublisherModule.unFollowEventsPublish(
                        match
                            { it.follower == follower && it.followee == followee },
                    )
                } returns Unit
                sut.unFollow(follower = follower, followee = followee)
                then("처리") {
                    val result = repository.findAll()
                    result.size shouldBe 0
                }
            }
        }

        given("존재 하지 않는 데이터 unfollow") {
            val follower = UserID("yoonhc@ncsoft.com")
            val followee = UserID("scpark88@ncsoft.com")
            `when`("호출") {
                then("NotFoundFollowException 발생") {
                    shouldThrowExactly<NotFoundFollowException> {
                        sut.unFollow(follower = follower, followee = followee)
                    }
                }
            }
        }
    })

class UnFollowServiceTestConfiguration {
    @Bean
    fun eventPublisherModule(): EventPublisherModule = mockk()
}
