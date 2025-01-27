package team_json_delivery.sns_b.domain.post.model.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "게시글 생성 응답")
data class CreatePostResponse(
    @Schema(description = "생성된 게시글 ID", example = "1")
    val id: Long
)
