package team_json_delivery.sns_b.domain.follow.repository

import org.springframework.data.jpa.repository.JpaRepository
import team_json_delivery.sns_b.domain.follow.domain.Follow

interface FollowRepository : JpaRepository<Follow, Long>
