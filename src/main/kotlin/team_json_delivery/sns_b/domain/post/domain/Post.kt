package team_json_delivery.sns_b.domain.post.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import team_json_delivery.sns_b.global.entity.BaseEntity

@Entity
@Table(name = "feed_post")
class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val content: String,
): BaseEntity()
