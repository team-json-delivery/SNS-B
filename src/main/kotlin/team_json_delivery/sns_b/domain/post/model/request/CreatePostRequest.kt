package team_json_delivery.sns_b.domain.post.model.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "게시글 생성 요청")
data class CreatePostRequest(
    @Schema(description = "게시글 내용", example = "안녕하세요")
    val content: String,
)
