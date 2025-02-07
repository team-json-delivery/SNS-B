package team_json_delivery.sns_b.domain.follow.model.response

import io.swagger.v3.oas.annotations.media.Schema
import team_json_delivery.sns_b.domain.follow.domain.Follow

@Schema(description = "팔로우 정보 조회 응답")
data class GetFollowersResponse(
    @Schema(description = "사용자 ID")
    val userId: String,
    @Schema(description = "팔로우 사용자 ID")
    val followerIds: Set<String>,
) {
    companion object {
        fun from(
            userId: String,
            list: List<Follow>,
        ): GetFollowersResponse =
            GetFollowersResponse(
                userId = userId,
                followerIds = list.map { it.follower.value }.toSet(),
            )
    }
}
