package team_json_delivery.sns_b.domain.follow.service

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import team_json_delivery.sns_b.domain.follow.domain.vo.UserID
import team_json_delivery.sns_b.domain.follow.exception.DuplicatedFollowException
import team_json_delivery.sns_b.domain.follow.exception.SelfFollowNotAllowedException
import team_json_delivery.sns_b.domain.follow.module.event.EventPublisherModule
import team_json_delivery.sns_b.domain.follow.repository.FollowRepository

@Import(FollowService::class, FollowServiceTestConfiguration::class)
@DataJpaTest
class FollowServiceTest(
    val sut: FollowService,
    val repository: FollowRepository,
    val eventPublisherModule: EventPublisherModule,
) : BehaviorSpec({
        afterEach { clearAllMocks() }
        given("follow 데이터 생성") {
            val follower = UserID("yoonhc@ncsoft.com")
            val followee = UserID("scpark88@ncsoft.com")
            `when`("호출") {
                every {
                    eventPublisherModule.followEventsPublish(
                        match
                            { it.follower == follower && it.followee == followee },
                    )
                } returns Unit
                sut.follow(follower = follower, followee = followee)
                then("해당 저장소에 데이터 존재") {
                    val result = repository.findAll()
                    result.size shouldBe 1
                    result.first().follower shouldBe follower
                    result.first().followee shouldBe followee
                }
            }
        }

        given("존재 하는 follow 데이터 생성") {
            val follower = UserID("yoonhc@ncsoft.com")
            val followee = UserID("scpark88@ncsoft.com")
            every {
                eventPublisherModule.followEventsPublish(
                    match
                        { it.follower == follower && it.followee == followee },
                )
            } returns Unit
            sut.follow(follower = follower, followee = followee)
            `when`("호출") {
                then("DuplicatedDataException 발생") {
                    shouldThrowExactly<DuplicatedFollowException> {
                        sut.follow(follower = follower, followee = followee)
                    }
                    verify(exactly = 1) {
                        // 선 입력때 추가
                        eventPublisherModule.followEventsPublish(
                            match
                                { it.follower == follower && it.followee == followee },
                        )
                    }
                }
            }
        }

        given("자기 자신을 follow하는 데이터 생성") {
            val follower = UserID("yoonhc@ncsoft.com")
            val followee = UserID("yoonhc@ncsoft.com")
            `when`("호출") {
                then("SelfFollowNotAllowedException 발생") {
                    shouldThrowExactly<SelfFollowNotAllowedException> {
                        sut.follow(follower = follower, followee = followee)
                    }
                }
            }
        }
    })

class FollowServiceTestConfiguration {
    @Bean
    fun eventPublisherModule(): EventPublisherModule = mockk()
}
