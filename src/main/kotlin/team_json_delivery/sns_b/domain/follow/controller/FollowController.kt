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
@RequestMapping("/api/v1/users/{userID}")
class FollowController(
    val followService: FollowService,
    val readFollowService: ReadFollowService,
) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/follow/{followeeID}")
    fun follow(
        @PathVariable userID: Long,
        @PathVariable followeeID: Long,
    ) {
        followService.follow(follower = UserID(userID), followee = UserID(followeeID))
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/follow/{followeeID}")
    fun unFollow(
        @PathVariable userID: Long,
        @PathVariable followeeID: Long,
    ) {
        followService.unFollow(follower = UserID(userID), followee = UserID(followeeID))
    }

    @GetMapping("/followers")
    fun getFollowers(
        @PathVariable userID: Long,
    ): WebResponse<GetFollowersResponse> {
        val userWithFollowers = readFollowService.readFollwers(UserID(userID))
        return WebResponse.success(GetFollowersResponse.from(userWithFollowers))
    }

    @GetMapping("/followings")
    fun getFollowings(
        @PathVariable userID: Long,
    ): WebResponse<GetFollowingsResponse> {
        val userWithFollowings = readFollowService.readFollowings(UserID(userID))
        return WebResponse.success(GetFollowingsResponse.from(userWithFollowings))
    }
}
