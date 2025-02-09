package team_json_delivery.sns_b.domain.user.model.response

import team_json_delivery.sns_b.domain.user.domain.User

data class GetUserResponse (
    val id: Long,
    val userName: String
) {
    companion object {
        fun from(user: User): GetUserResponse {
            return GetUserResponse(
                id = user.id,
                userName = user.userName
            )
        }
    }
}