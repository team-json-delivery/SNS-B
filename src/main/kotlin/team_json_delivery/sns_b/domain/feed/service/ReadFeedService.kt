package team_json_delivery.sns_b.domain.feed.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team_json_delivery.sns_b.domain.feed.domain.Feed
import team_json_delivery.sns_b.domain.feed.domain.vo.UserID
import team_json_delivery.sns_b.domain.feed.model.dto.FeedsPageRequestDto
import team_json_delivery.sns_b.domain.feed.repository.FeedRepository

@Service
@Transactional(readOnly = true)
class ReadFeedService(
    private val repository: FeedRepository,
) {
    fun findFeedsFor(
        user: UserID,
        pageRequest: FeedsPageRequestDto,
    ): Page<Feed> = repository.findAllByFollowerOrderByUpdatedAtDesc(user.value, PageRequest.of(pageRequest.page, pageRequest.size))
}
