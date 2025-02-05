package team_json_delivery.sns_b.domain.follow.domain

import team_json_delivery.sns_b.domain.follow.domain.vo.UserID

class UserWithFollowers(
    val user: UserID,
    val followers: Set<UserID>,
)
