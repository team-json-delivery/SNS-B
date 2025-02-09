package team_json_delivery.sns_b.domain.feed.model.reponse

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.data.domain.Page
import team_json_delivery.sns_b.domain.feed.domain.Feed

@Schema(description = "피드 조회 응답")
data class GetFeedsResponse(
    @Schema(description = "사용자 ID")
    val userId: String,
    @Schema(description = "포스트들 ID")
    val postId: Set<String>,
    @Schema(description = "현재 페이지 (0부터 시작)")
    val number: Int,
    @Schema(description = "페이지 크기")
    val size: Int,
    @Schema(description = "전체 페이지 수")
    val totalPage: Int,
    @Schema(description = "전체 게시글 수")
    val totalElements: Long,
) {
    companion object {
        fun from(
            userId: String,
            feeds: Page<Feed>,
        ): GetFeedsResponse =
            GetFeedsResponse(
                userId = userId,
                postId = feeds.content.map { it.post.value }.toSet(),
                totalPage = feeds.totalPages,
                totalElements = feeds.totalElements,
                number = feeds.number,
                size = feeds.size,
            )
    }
}
