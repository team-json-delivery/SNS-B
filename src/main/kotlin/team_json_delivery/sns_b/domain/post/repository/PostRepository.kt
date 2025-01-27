package team_json_delivery.sns_b.domain.post.repository

import org.springframework.data.jpa.repository.JpaRepository
import team_json_delivery.sns_b.domain.post.domain.Post

interface PostRepository : JpaRepository<Post, Long>
