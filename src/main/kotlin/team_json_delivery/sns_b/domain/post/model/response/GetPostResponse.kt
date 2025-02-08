package team_json_delivery.sns_b.domain.post.model.response

import io.swagger.v3.oas.annotations.media.Schema
import team_json_delivery.sns_b.domain.post.model.dto.PostDto

@Schema(description = "게시글 조회 응답")
data class GetPostResponse(
    @Schema(description = "생성된 게시글 ID", example = "1")
    val id: Long,
    @Schema(description = "좋아요 수", example = "10")
    val likeCount: Long,
    @Schema(description = "게시글 내용", example = "안녕하세요")
    val content: String,
) {
    companion object {
        fun from(dto: PostDto): GetPostResponse {
            return GetPostResponse(
                id = dto.id,
                likeCount = dto.likeCount,
                content = dto.content,
            )
        }
    }
}
