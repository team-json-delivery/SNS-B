package team_json_delivery.sns_b.domain.feed.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import team_json_delivery.sns_b.domain.feed.domain.Feed
import team_json_delivery.sns_b.domain.feed.domain.FeedTest
import team_json_delivery.sns_b.domain.feed.domain.vo.UserID
import team_json_delivery.sns_b.domain.feed.model.dto.FeedsPageRequestDto
import team_json_delivery.sns_b.domain.feed.model.reponse.GetFeedsResponse
import team_json_delivery.sns_b.domain.feed.service.ReadFeedService

@WebMvcTest(FeedController::class)
@Import(FeedControllerTestConfiguration::class)
class FeedControllerTest(
    private val mockMvc: MockMvc,
    private val readFeedService: ReadFeedService,
) : BehaviorSpec({
        afterEach {
            clearAllMocks()
        }
        val objectMapper = jacksonObjectMapper()
        given("Feed 조회") {
            val userId = UserID("yoonhc@ncsoft.com")
            val feeds = mutableListOf<Feed>()
            val page = 0
            val size = 10
            val totalElements = 100L
            val baseUrl = "/api/v1/users/{userId}"
            val getFeedsUrl = "$baseUrl/feeds"

            repeat(size) {
                feeds.add(FeedTest.of(follower = userId))
            }

            `when`("호출") {
                every { readFeedService.findFeedsFor(userId, pageRequest = FeedsPageRequestDto(page = page, size = size)) } returns
                    PageImpl(feeds, PageRequest.of(page, size), totalElements)

                val response =
                    mockMvc
                        .perform(get(getFeedsUrl, userId.value).param("page", "$page").param("size", "$size"))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isOk)
                        .andReturn()
                        .response
                then("데이터 반환") {
                    val getFeedsResponse = objectMapper.readValue(response.contentAsString, GetFeedsResponse::class.java)
                    getFeedsResponse.size shouldBe size
                    getFeedsResponse.userId shouldBe userId.value
                    getFeedsResponse.totalElements shouldBe totalElements
                }
            }
        }
    })

@TestConfiguration
class FeedControllerTestConfiguration {
    @Bean
    fun readFeedService(): ReadFeedService = mockk()
}
