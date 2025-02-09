package team_json_delivery.sns_b.domain.feed.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.date.shouldBeAfter
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.delay
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import team_json_delivery.sns_b.domain.feed.domain.Feed
import team_json_delivery.sns_b.domain.feed.domain.FeedTest
import team_json_delivery.sns_b.domain.feed.domain.vo.UserID
import team_json_delivery.sns_b.domain.feed.model.dto.FeedsPageRequestDto
import team_json_delivery.sns_b.domain.feed.repository.FeedRepository
import kotlin.math.ceil

@Import(ReadFeedService::class)
@DataJpaTest
class ReadFeedServiceTest(
    val sut: ReadFeedService,
    val repository: FeedRepository,
) : BehaviorSpec({
        val user = UserID("yoonhc@ncsoft.com")
        given("피드 조회") {
            val list = mutableListOf<Feed>()
            val page = 0
            val size = 10
            val totalElements = 50
            repeat(totalElements) {
                delay(1)
                list.add(
                    FeedTest.of(
                        follower = user,
                    ),
                )
            }
            repository.saveAll(list)
            val pageRequest = FeedsPageRequestDto(page = page, size = size)
            `when`("호출") {
                val feedPage =
                    sut.findFeedsFor(
                        user = user,
                        pageRequest = pageRequest,
                    )
                then("feed 데이터 반환") {
                    feedPage.totalPages shouldBe ceil((totalElements / size).toDouble())
                    feedPage.content.first().updatedAt shouldBeAfter feedPage.content[2].updatedAt
                }
            }
        }
    })
