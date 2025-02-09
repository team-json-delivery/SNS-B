package team_json_delivery.sns_b.domain.user.model.request

data class CreateUserRequest(
    val id: Long,
    val userName: String
)