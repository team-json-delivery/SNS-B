package team_json_delivery.sns_b.domain.post.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import team_json_delivery.sns_b.domain.post.model.response.GetPostResponse
import team_json_delivery.sns_b.domain.post.service.GetPostService
import team_json_delivery.sns_b.global.model.response.WebResponse

@RestController
@Tag(name = "[User API] 게시글")
class GetPostController(
    private val getPostService: GetPostService
) {
    @Operation(summary = "게시글 조회")
    @GetMapping("/api/v1/posts/{postId}")
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
