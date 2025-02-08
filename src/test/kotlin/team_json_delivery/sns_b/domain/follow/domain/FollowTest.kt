package team_json_delivery.sns_b.domain.follow.domain

import team_json_delivery.sns_b.domain.follow.domain.vo.UserID
import java.util.*

class FollowTest {
    companion object {
        fun of(
            id: Long = 0,
            follower: UserID = UserID(UUID.randomUUID().toString()),
            followee: UserID = UserID(UUID.randomUUID().toString()),
        ): Follow =
            Follow(
                id = id,
                follower = follower,
                followee = followee,
            )
    }
}
