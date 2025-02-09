package team_json_delivery.sns_b.domain.follow.domain

import jakarta.persistence.*
import team_json_delivery.sns_b.domain.follow.domain.vo.UserID
import team_json_delivery.sns_b.global.entity.BaseEntity

@Entity
@Table(
    name = "follow",
    indexes = [
        Index(name = "idx_follower", columnList = "follower"),
        Index(name = "idx_followee", columnList = "followee"),
    ],
    uniqueConstraints = [
        UniqueConstraint(name = "uk_follower_followee", columnNames = ["follower", "followee"]),
    ],
)
class Follow(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(name = "follower", nullable = false) // 팔로우를 하는 사람
    val follower: UserID,
    @Column(name = "followee", nullable = false) // 팔로우 당하는 사람
    val followee: UserID,
) : BaseEntity()
