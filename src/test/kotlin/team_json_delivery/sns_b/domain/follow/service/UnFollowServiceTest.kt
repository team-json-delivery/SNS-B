package team_json_delivery.sns_b.domain.follow.service

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import team_json_delivery.sns_b.domain.follow.domain.vo.UserID
import team_json_delivery.sns_b.domain.follow.exception.NotFoundFollowException
import team_json_delivery.sns_b.domain.follow.repository.FollowRepository

@Import(UnFollowService::class, FollowService::class)
@DataJpaTest
class UnFollowServiceTest(
    val sut: UnFollowService,
    val followService: FollowService,
    val repository: FollowRepository,
) : BehaviorSpec({
        given("데이터 unfollow") {
            val follower = UserID("yoonhc@ncsoft.com")
            val followee = UserID("scpark88@ncsoft.com")
            followService.follow(follower = follower, followee = followee)
            `when`("호출") {
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
