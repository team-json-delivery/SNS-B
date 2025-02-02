package team_json_delivery.sns_b.domain.follow.model.response

import io.swagger.v3.oas.annotations.media.Schema
import team_json_delivery.sns_b.domain.follow.domain.UserWithFollowings

@Schema(description = "팔로잉 정보 조회 응답")
data class GetFollowingsResponse(
    @Schema(description = "사용자 ID")
    val userId: Long,
    @Schema(description = "팔로잉 사용자 ID")
    val followingIds: Set<Long>,
) {
    companion object {
        fun from(userWithFollowings: UserWithFollowings): GetFollowingsResponse =
            GetFollowingsResponse(
                userId = userWithFollowings.user.value,
                followingIds = userWithFollowings.followings.map { it.value }.toSet(),
            )
    }
}
