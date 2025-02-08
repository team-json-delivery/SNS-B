package team_json_delivery.sns_b.domain.follow.model.response

import io.swagger.v3.oas.annotations.media.Schema
import team_json_delivery.sns_b.domain.follow.domain.Follow

@Schema(description = "팔로잉 정보 조회 응답")
data class GetFolloweesResponse(
    @Schema(description = "사용자 ID")
    val userId: String,
    @Schema(description = "팔로잉 사용자 ID")
    val followeeIds: Set<String>,
) {
    companion object {
        fun from(
            userId: String,
            list: List<Follow>,
        ): GetFolloweesResponse =
            GetFolloweesResponse(
                userId = userId,
                followeeIds = list.map { it.followee.value }.toSet(),
            )
    }
}
