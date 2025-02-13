package team_json_delivery.sns_b.domain.like.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import team_json_delivery.sns_b.domain.like.model.command.CreateLikedPostCommand
import team_json_delivery.sns_b.domain.like.model.command.DeleteLikedPostCommand
import team_json_delivery.sns_b.domain.like.model.response.CreateLikedPostResponse
import team_json_delivery.sns_b.domain.like.service.LikeCommandService
import team_json_delivery.sns_b.domain.user.model.command.CreateUserCommand
import team_json_delivery.sns_b.domain.user.model.request.CreateUserRequest
import team_json_delivery.sns_b.domain.user.model.response.CreateUserResponse
import team_json_delivery.sns_b.domain.user.model.response.GetUserResponse
import team_json_delivery.sns_b.domain.user.service.CreateUserService
import team_json_delivery.sns_b.domain.user.service.GetUserService
import team_json_delivery.sns_b.global.model.response.WebResponse

@RestController
@RequestMapping("/api/v1/users")
class LikeCommandController(
    private val likeCommandService: LikeCommandService
) {
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "유저가 글을 좋아요")
    @PostMapping("/{userId}/posts/{postId}/likes")
    fun like(
        @Schema(description = "유저 번호", example = "123")
        @PathVariable userId: Long,
        @Schema(description = "게시글 번호", example = "123")
        @PathVariable postId: Long
    ): WebResponse<CreateLikedPostResponse> {
        val like = likeCommandService.like(CreateLikedPostCommand(userId, postId))
        return WebResponse.success(
            CreateLikedPostResponse(like.id)
        )
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "유저가 글을 좋아요 취소")
    @DeleteMapping("/{userId}/posts/{postId}/likes")
    fun cancelLike(
        @Schema(description = "유저 번호", example = "123")
        @PathVariable userId: Long,
        @Schema(description = "게시글 번호", example = "123")
        @PathVariable postId: Long
    ): WebResponse<Unit> {
        likeCommandService.cancelLike(DeleteLikedPostCommand(userId, postId))
        return WebResponse.success(Unit)
    }
}