package team_json_delivery.sns_b.global.event

data class FollowEventDto(
    val follower: String,
    val followee: String,
    val type: FollowEventType,
)
