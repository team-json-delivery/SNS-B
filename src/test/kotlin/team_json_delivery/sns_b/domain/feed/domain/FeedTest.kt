package team_json_delivery.sns_b.domain.feed.domain

import org.junit.jupiter.api.Assertions.*
import team_json_delivery.sns_b.domain.feed.domain.vo.PostID
import team_json_delivery.sns_b.domain.feed.domain.vo.UserID
import java.util.UUID

class FeedTest {
    companion object {
        fun of(
            id: Long = 0,
            follower: UserID = UserID(UUID.randomUUID().toString()),
            followee: UserID = UserID(UUID.randomUUID().toString()),
            postId: PostID = PostID(UUID.randomUUID().toString()),
        ): Feed =
            Feed(
                id = id,
                follower = follower,
                followee = followee,
                postId = postId,
            )
    }
}
