package team_json_delivery.sns_b.domain.feed.domain

import jakarta.persistence.*
import team_json_delivery.sns_b.domain.feed.domain.vo.PostID
import team_json_delivery.sns_b.domain.feed.domain.vo.UserID
import team_json_delivery.sns_b.global.entity.BaseEntity

@Entity
@Table(
    name = "feed_post",
    indexes = [
        Index(name = "idx_follower_followee", columnList = "follower, followee"),
        Index(name = "idx_follower", columnList = "follower"),
    ],
    uniqueConstraints = [
        UniqueConstraint(name = "uk_follower_followee_post", columnNames = ["follower", "followee", "post"]),
    ],
)
class Feed(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val follower: UserID,
    val followee: UserID,
    val post: PostID,
) : BaseEntity()
