package team_json_delivery.sns_b.domain.post.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team_json_delivery.sns_b.domain.post.model.command.CreatePostCommand
import team_json_delivery.sns_b.domain.post.model.request.CreatePostRequest
import team_json_delivery.sns_b.domain.post.model.response.CreatePostResponse
import team_json_delivery.sns_b.domain.post.model.response.GetPostResponse
import team_json_delivery.sns_b.domain.post.service.CreatePostService
import team_json_delivery.sns_b.domain.post.service.GetPostService
import team_json_delivery.sns_b.global.model.response.WebResponse

@RestController
@RequestMapping("/api/v1/posts")
@Tag(name = "[User API] 게시글")
class PostController(
    private val createPostService: CreatePostService,
    private val getPostService: GetPostService
) {
    @Operation(summary = "게시글 생성")
    @PostMapping
    fun createPost(request: CreatePostRequest): WebResponse<CreatePostResponse> {
        val postId = createPostService.create(
            CreatePostCommand(request.content)
        )

        return WebResponse.success(
            CreatePostResponse(postId)
        )
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
