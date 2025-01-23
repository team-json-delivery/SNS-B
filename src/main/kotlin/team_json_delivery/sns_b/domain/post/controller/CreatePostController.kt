package team_json_delivery.sns_b.domain.post.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import team_json_delivery.sns_b.domain.post.model.command.CreatePostCommand
import team_json_delivery.sns_b.domain.post.model.request.CreatePostRequest
import team_json_delivery.sns_b.domain.post.model.response.CreatePostResponse
import team_json_delivery.sns_b.domain.post.service.CreatePostService
import team_json_delivery.sns_b.global.model.response.WebResponse

@RestController
@Tag(name = "[User API] 게시글")
class CreatePostController(
    private val createPostService: CreatePostService
) {
    @Operation(summary = "게시글 생성")
    @PostMapping("/api/v1/posts")
    fun createPost(request: CreatePostRequest): WebResponse<CreatePostResponse> {
        val postId = createPostService.create(
            CreatePostCommand(request.content)
        )

        return WebResponse.success(
            CreatePostResponse(postId)
        )
    }
}
