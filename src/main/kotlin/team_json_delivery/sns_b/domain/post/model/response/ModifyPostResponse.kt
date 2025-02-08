package team_json_delivery.sns_b.domain.post.model.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "게시글 수정 응답")
data class ModifyPostResponse(
    @Schema(description = "수정된 게시글 ID", example = "1")
    val id: Long
)
