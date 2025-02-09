package team_json_delivery.sns_b.domain.follow.model.response

import io.swagger.v3.oas.annotations.media.Schema
import team_json_delivery.sns_b.domain.follow.domain.UserWithFollowers

@Schema(description = "팔로우 정보 조회 응답")
data class GetFollowersResponse(
    @Schema(description = "사용자 ID")
    val userId: Long,
    @Schema(description = "팔로우 사용자 ID")
    val followerIds: Set<Long>,
) {
    companion object {
        fun from(userWithFollowers: UserWithFollowers): GetFollowersResponse =
            GetFollowersResponse(
                userId = userWithFollowers.user.value,
                followerIds = userWithFollowers.followers.map { it.value }.toSet(),
            )
    }
}
