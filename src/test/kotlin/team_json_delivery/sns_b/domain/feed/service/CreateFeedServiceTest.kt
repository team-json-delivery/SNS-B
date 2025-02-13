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

@Import(CreateFeedService::class, ReadFeedService::class)
@DataJpaTest
class CreateFeedServiceTest(
    val sut: CreateFeedService,
    val readFeedService: ReadFeedService,
) : BehaviorSpec({
        val user = UserID("yoonhc@ncsoft.com")
        given("피드 생성") {
            val list = mutableListOf<Feed>()
            val totalElements = 100
            repeat(totalElements) {
                list.add(
                    FeedTest.of(
                        follower = user,
                    ),
                )
            }
            `when`("호출") {
                sut.createFeeds(list)
                then("feed 데이터 반환") {
                    val res = readFeedService.findFeedsFor(user, pageRequest = FeedsPageRequestDtoTest.of())
                    res.totalElements shouldBe list.size
                }
            }
        }

        given("피드 생성(중복 데이터)") {
            val list = mutableListOf<Feed>()
            val totalElements = 100
            val duplicateFeed =
                FeedTest.of(
                    follower = user,
                )
            val duplicateCount = 50
            repeat(totalElements) {
                list.add(
                    FeedTest.of(
                        follower = user,
                    ),
                )
            }
            repeat(duplicateCount) {
                list.add(
                    duplicateFeed,
                )
            }

            `when`("호출") {
                sut.createFeeds(list)
                then("중복된 feed 데이터 제외 후 반환") {
                    val res = readFeedService.findFeedsFor(user, pageRequest = FeedsPageRequestDtoTest.of())
                    res.totalElements shouldBe list.size - duplicateCount + 1
                }
            }
        }
    })
