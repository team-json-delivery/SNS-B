package team_json_delivery.sns_b.domain.follow.domain

import team_json_delivery.sns_b.domain.follow.domain.vo.UserID

class Follow(
    val follower: UserID, // 팔로우를 하는 사람
    val followee: UserID, // 팔로우 당하는 사람
)
