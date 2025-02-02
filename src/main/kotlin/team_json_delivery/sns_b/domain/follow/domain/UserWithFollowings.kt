package team_json_delivery.sns_b.domain.follow.domain

import team_json_delivery.sns_b.domain.follow.domain.vo.UserID

class UserWithFollowings(
    val user: UserID,
    val followings: Set<UserID>,
)
