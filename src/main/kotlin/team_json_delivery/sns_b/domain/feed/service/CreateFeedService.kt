package team_json_delivery.sns_b.domain.feed.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team_json_delivery.sns_b.domain.feed.domain.Feed
import team_json_delivery.sns_b.domain.feed.repository.FeedRepository

@Service
@Transactional
class CreateFeedService(
    private val repository: FeedRepository,
) {
    fun createFeeds(feeds: List<Feed>): List<Feed> = repository.saveAll(feeds)
}
