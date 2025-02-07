package team_json_delivery.sns_b.domain.follow.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import team_json_delivery.sns_b.domain.follow.domain.vo.UserID
import team_json_delivery.sns_b.domain.follow.model.response.GetFollowersResponse
import team_json_delivery.sns_b.domain.follow.model.response.GetFollowingsResponse
import team_json_delivery.sns_b.domain.follow.service.FollowService
import team_json_delivery.sns_b.domain.follow.service.ReadFollowService
import team_json_delivery.sns_b.global.model.response.WebResponse

@RestController
@RequestMapping("/api/v1/users/{userId}")
class FollowController(
    val followService: FollowService,
    val readFollowService: ReadFollowService,
) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/follow/{followeeId}")
    fun follow(
        @PathVariable userId: String,
        @PathVariable followeeId: String,
    ) {
        followService.follow(follower = UserID(userId), followee = UserID(followeeId))
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/follow/{followeeId}")
    fun unFollow(
        @PathVariable userId: String,
        @PathVariable followeeId: String,
    ) {
        followService.unFollow(follower = UserID(userId), followee = UserID(followeeId))
    }

    @GetMapping("/followers")
    fun getFollowers(
        @PathVariable userId: String,
    ): WebResponse<GetFollowersResponse> {
        val userWithFollowers = readFollowService.getFollowers(UserID(userId))
        return WebResponse.success(GetFollowersResponse.from(userId, userWithFollowers))
    }

    @GetMapping("/followings")
    fun getFollowings(
        @PathVariable userId: String,
    ): WebResponse<GetFollowingsResponse> {
        val followings = readFollowService.getFollowings(UserID(userId))
        return WebResponse.success(GetFollowingsResponse.from(userId, followings))
    }
}
