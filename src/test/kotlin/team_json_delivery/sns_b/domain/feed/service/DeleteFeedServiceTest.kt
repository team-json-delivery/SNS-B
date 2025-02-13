package team_json_delivery.sns_b.domain.feed.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.*
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import team_json_delivery.sns_b.domain.feed.domain.Feed
import team_json_delivery.sns_b.domain.feed.domain.FeedTest
import team_json_delivery.sns_b.domain.feed.domain.vo.UserID
import team_json_delivery.sns_b.domain.feed.model.dto.FeedsPageRequestDtoTest

@Import(DeleteFeedService::class, ReadFeedService::class)
@DataJpaTest
class DeleteFeedServiceTest(
    val sut: DeleteFeedService,
    val readFeedService: ReadFeedService,
) : BehaviorSpec({
        val follower = UserID("yoonhc@ncsoft.com")
        val followee = UserID("scpark88@ncsoft.com")
        given("피드 삭제") {
            val list = mutableListOf<Feed>()
            val totalElements = 100
            repeat(totalElements) {
                list.add(
                    FeedTest.of(
                        follower = follower,
                        followee = followee,
                    ),
                )
            }

            `when`("호출") {
                sut.deleteFeedsFor(follower = follower, followee = followee)
                then("삭제 처리") {
                    val res = readFeedService.findFeedsFor(follower, pageRequest = FeedsPageRequestDtoTest.of())
                    res.totalElements shouldBe 0
                }
            }
        }
    })
