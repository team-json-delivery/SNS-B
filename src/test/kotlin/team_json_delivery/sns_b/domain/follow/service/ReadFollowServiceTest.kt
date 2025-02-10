package team_json_delivery.sns_b.domain.follow.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import team_json_delivery.sns_b.domain.follow.domain.Follow
import team_json_delivery.sns_b.domain.follow.domain.vo.UserID
import team_json_delivery.sns_b.domain.follow.repository.FollowRepository

@Import(ReadFollowService::class)
@DataJpaTest
class ReadFollowServiceTest(
    val sut: ReadFollowService,
    val repository: FollowRepository,
) : BehaviorSpec({
        given("findFollowersFor 데이터 조회") {
            val followers = listOf(UserID("abs@ncsoft.com"), UserID("cccc@ncsoft.com"), UserID("aaa@ncsoft.com"))
            val followee = UserID("yoonhc@ncsoft.com")
            followers.forEach {
                repository.save(Follow(follower = it, followee = followee))
            }
            `when`("호출") {
                val list = sut.findFollowersFor(user = followee)
                then("데이터 반환") {
                    list.size shouldBe followers.size
                    list.forEach {
                        it.followee shouldBe followee
                        followers shouldContain it.follower
                    }
                }
            }
        }
        given("findFolloweesFor 데이터 조회") {
            val followees = listOf(UserID("abs@ncsoft.com"), UserID("cccc@ncsoft.com"), UserID("aaa@ncsoft.com"))
            val follower = UserID("yoonhc@ncsoft.com")
            followees.forEach {
                repository.save(Follow(follower = follower, followee = it))
            }
            `when`("호출") {
                val list = sut.findFolloweesFor(user = follower)
                then("데이터 반환") {
                    list.size shouldBe followees.size
                    list.forEach {
                        it.follower shouldBe follower
                        followees shouldContain it.followee
                    }
                }
            }
        }
    })
