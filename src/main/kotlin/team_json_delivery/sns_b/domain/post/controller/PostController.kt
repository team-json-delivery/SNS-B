package team_json_delivery.sns_b.domain.post.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team_json_delivery.sns_b.domain.post.model.command.CreatePostCommand
import team_json_delivery.sns_b.domain.post.model.command.ModifyPostCommand
import team_json_delivery.sns_b.domain.post.model.request.CreatePostRequest
import team_json_delivery.sns_b.domain.post.model.request.ModifyPostRequest
import team_json_delivery.sns_b.domain.post.model.response.CreatePostResponse
import team_json_delivery.sns_b.domain.post.model.response.GetPostResponse
import team_json_delivery.sns_b.domain.post.model.response.ModifyPostResponse
import team_json_delivery.sns_b.domain.post.service.CreatePostService
import team_json_delivery.sns_b.domain.post.service.DeletePostService
import team_json_delivery.sns_b.domain.post.service.GetPostService
import team_json_delivery.sns_b.domain.post.service.ModifyPostService
import team_json_delivery.sns_b.global.model.response.WebResponse

@RestController
@RequestMapping("/api/v1/posts")
@Tag(name = "[User API] 게시글")
class PostController(
    private val createPostService: CreatePostService,
    private val modifyPostService: ModifyPostService,
    private val deletePostService: DeletePostService,
    private val getPostService: GetPostService
) {
    @Operation(summary = "게시글 생성")
    @PostMapping
    fun createPost(@RequestBody request: CreatePostRequest): WebResponse<CreatePostResponse> {
        val postId = createPostService.create(
            CreatePostCommand(request.content)
        )

        return WebResponse.success(
            CreatePostResponse(postId)
        )
    }

    @Operation(summary = "게시글 수정")
    @PutMapping("/{postId}")
    fun modifyPost(
        @Schema(description = "게시글 번호", example = "123")
        @PathVariable postId: Long,
        @RequestBody request: ModifyPostRequest
    ): WebResponse<ModifyPostResponse> {
        modifyPostService.modify(
            postId = postId,
            command = ModifyPostCommand(request.content)
        )

        return WebResponse.success(
            ModifyPostResponse(postId)
        )
    }

    @Operation(summary = "게시글 삭제")
    @DeleteMapping("/{postId}")
    fun deletePost(
        @Schema(description = "게시글 번호", example = "123")
        @PathVariable postId: Long,
    ): WebResponse<Unit> {
        deletePostService.delete(postId)

        return WebResponse.success(null)
    }

    @Operation(summary = "게시글 조회")
    @GetMapping("/{postId}")
    fun getPost(
        @Schema(description = "게시글 번호", example = "123")
        @PathVariable postId: Long
    ): WebResponse<GetPostResponse> {
        val post = getPostService.getPost(postId)

        return WebResponse.success(
            GetPostResponse.from(post)
        )
    }
}
