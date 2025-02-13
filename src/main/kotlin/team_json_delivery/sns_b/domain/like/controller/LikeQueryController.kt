package team_json_delivery.sns_b.domain.like.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team_json_delivery.sns_b.domain.like.model.response.GetUserLikedPostsResponse
import team_json_delivery.sns_b.domain.like.service.LikeQueryService
import team_json_delivery.sns_b.global.model.response.WebResponse

@RestController
@RequestMapping("/api/v1/users")
class LikeQueryController(
    private val likeQueryService: LikeQueryService
) {
    @Operation(summary = "유저가 좋아요한 글 조회")
    @GetMapping("/{userId}/posts/likes")
    fun getLikes(
        @Schema(description = "유저 번호", example = "123")
        @PathVariable userId: Long
    ): WebResponse<GetUserLikedPostsResponse> {
        val likes = likeQueryService.getLikedPosts(userId)
        return WebResponse.success(
            GetUserLikedPostsResponse.from(likes)
        )
    }

    @Operation(summary = "유저가 글을 좋아요 했는지 조회")
    @GetMapping("/{userId}/posts/{postId}/likes")
    fun hasLike(
        @Schema(description = "유저 번호", example = "123")
        @PathVariable userId: Long,
        @Schema(description = "게시글 번호", example = "123")
        @PathVariable postId: Long
    ): WebResponse<Boolean> {
        val like = likeQueryService.hasLike(userId, postId)
        return WebResponse.success(like)
    }
}