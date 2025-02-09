package team_json_delivery.sns_b.domain.follow.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.isEqualTo
import team_json_delivery.sns_b.domain.follow.domain.FollowTest
import team_json_delivery.sns_b.domain.follow.domain.vo.UserID
import team_json_delivery.sns_b.domain.follow.exception.DuplicatedFollowException
import team_json_delivery.sns_b.domain.follow.exception.NotFoundFollowException
import team_json_delivery.sns_b.domain.follow.exception.SelfFollowNotAllowedException
import team_json_delivery.sns_b.domain.follow.model.response.GetFolloweesResponse
import team_json_delivery.sns_b.domain.follow.model.response.GetFollowersResponse
import team_json_delivery.sns_b.domain.follow.service.FollowService
import team_json_delivery.sns_b.domain.follow.service.ReadFollowService
import team_json_delivery.sns_b.domain.follow.service.UnFollowService
import team_json_delivery.sns_b.global.exception.GlobalExceptionHandler.Companion.ERROR_CODE

@WebMvcTest(controllers = [FollowController::class])
@Import(FollowControllerTestConfiguration::class)
class FollowControllerTest(
    private val mockMvc: MockMvc,
    private val followService: FollowService,
    private val unfollowService: UnFollowService,
    private val readFollowService: ReadFollowService,
) : BehaviorSpec({
        val objectMapper = jacksonObjectMapper()
        val baseUrl = "/api/v1/users/{userId}"
        val followUrl = "$baseUrl/follow/{followeeId}"
        val unfollowUrl = "$baseUrl/follow/{followeeId}"
        val getFollowersUrl = "$baseUrl/followers"
        val getFolloweesUrl = "$baseUrl/followees"
        val userId = "yoonhc@ncsoft.com"

        afterEach {
            clearAllMocks()
        }

        given("Follow 생성") {
            val follower = UserID(userId)
            val followee = UserID("scpark88@ncsoft.com")
            `when`("호출") {
                every { followService.follow(follower = follower, followee = followee) } returns Unit
                then("성공") {
                    mockMvc
                        .perform(post(followUrl, follower.value, followee.value))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isCreated)
                }
            }

            `when`("호출(DuplicatedFollowException 발생)") {
                val ex = DuplicatedFollowException()
                every { followService.follow(follower = follower, followee = followee) } throws ex
                then("DuplicatedFollowException 처리") {
                    mockMvc
                        .perform(post(followUrl, follower.value, followee.value))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isEqualTo(ex.errorCode.status.value()))
                        .andExpect(jsonPath("$.$ERROR_CODE").value(ex.errorCode.code))
                }
            }

            `when`("호출(SelfFollowNotAllowedException 발생)") {
                val ex = SelfFollowNotAllowedException()
                every { followService.follow(follower = follower, followee = followee) } throws ex
                then("SelfFollowNotAllowedException 처리") {
                    mockMvc
                        .perform(post(followUrl, follower.value, followee.value))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isEqualTo(ex.errorCode.status.value()))
                        .andExpect(jsonPath("$.$ERROR_CODE").value(ex.errorCode.code))
                }
            }
        }

        given("UnFollow 생성") {
            val follower = UserID(userId)
            val followee = UserID("scpark88@ncsoft.com")
            `when`("호출") {
                every { unfollowService.unFollow(follower = follower, followee = followee) } returns Unit
                then("성공") {
                    mockMvc
                        .perform(delete(unfollowUrl, follower.value, followee.value))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isNoContent)
                }
            }

            `when`("호출(NotFoundFollowException 발생)") {
                val ex = NotFoundFollowException()
                every { unfollowService.unFollow(follower = follower, followee = followee) } throws ex
                then("NotFoundFollowException 처리") {
                    mockMvc
                        .perform(delete(unfollowUrl, follower.value, followee.value))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isEqualTo(ex.errorCode.status.value()))
                        .andExpect(jsonPath("$.$ERROR_CODE").value(ex.errorCode.code))
                }
            }
        }

        given("Followers 조회") {
            val list =
                listOf(
                    FollowTest.of(
                        followee = UserID(userId),
                    ),
                    FollowTest.of(
                        followee = UserID(userId),
                    ),
                    FollowTest.of(
                        followee = UserID(userId),
                    ),
                )
            `when`("호출") {
                every { readFollowService.findFollowersFor(UserID(userId)) } returns list
                val response =
                    mockMvc
                        .perform(get(getFollowersUrl, userId))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isOk)
                        .andReturn()
                        .response
                then("데이터 리턴") {
                    val followersDto = objectMapper.readValue(response.contentAsString, GetFollowersResponse::class.java)
                    followersDto.userId shouldBe userId
                    followersDto.followerIds.size shouldBe list.size
                    val followers = list.map { it.follower }
                    followersDto.followerIds.forEach {
                        followers shouldContain UserID(it)
                    }
                }
            }
        }

        given("Followees 조회") {
            val list =
                listOf(
                    FollowTest.of(
                        follower = UserID(userId),
                    ),
                    FollowTest.of(
                        follower = UserID(userId),
                    ),
                    FollowTest.of(
                        follower = UserID(userId),
                    ),
                )
            `when`("호출") {
                every { readFollowService.findFolloweesFor(UserID(userId)) } returns list
                val response =
                    mockMvc
                        .perform(get(getFolloweesUrl, userId))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isOk)
                        .andReturn()
                        .response
                then("데이터 리턴") {
                    val followeesDto = objectMapper.readValue(response.contentAsString, GetFolloweesResponse::class.java)
                    followeesDto.userId shouldBe userId
                    followeesDto.followeeIds.size shouldBe list.size
                    val followees = list.map { it.followee }
                    followeesDto.followeeIds.forEach {
                        followees shouldContain UserID(it)
                    }
                }
            }
        }
    })

@TestConfiguration
class FollowControllerTestConfiguration {
    @Bean
    fun followService(): FollowService = mockk()

    @Bean
    fun unfollowService(): UnFollowService = mockk()

    @Bean
    fun readFollowService(): ReadFollowService = mockk()
}
