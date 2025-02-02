package team_json_delivery.sns_b.domain.user.model.response

import team_json_delivery.sns_b.domain.user.domain.User

data class GetUserResponse (
    val id: String,
    val name: String
) {
    companion object {
        fun from(user: User): GetUserResponse {
            return GetUserResponse(
                id = user.id,
                name = user.name
            )
        }
    }
}