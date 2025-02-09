package team_json_delivery.sns_b.domain.feed.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import team_json_delivery.sns_b.domain.feed.domain.Feed

interface FeedRepository : JpaRepository<Feed, Long> {
    fun findAllByFollowerOrderByUpdatedAtDesc(
        follower: String,
        pageRequest: PageRequest,
    ): Page<Feed>

    fun deleteAllByFollowerAndFollowee(
        follower: String,
        followee: String,
    )
}
