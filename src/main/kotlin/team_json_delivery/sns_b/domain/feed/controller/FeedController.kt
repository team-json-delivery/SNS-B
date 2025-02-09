package team_json_delivery.sns_b.domain.feed.controller

import org.springframework.web.bind.annotation.*
import team_json_delivery.sns_b.domain.feed.domain.vo.UserID
import team_json_delivery.sns_b.domain.feed.model.dto.FeedsPageRequestDto
import team_json_delivery.sns_b.domain.feed.model.reponse.GetFeedsResponse
import team_json_delivery.sns_b.domain.feed.service.ReadFeedService

@RestController
@RequestMapping("/api/v1/users/{userID}")
class FeedController(
    private val service: ReadFeedService,
) {
    @GetMapping("/feeds")
    fun getFeeds(
        @PathVariable("userID") userID: String,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int,
    ): GetFeedsResponse {
        val feedPage = service.findFeedsFor(UserID(userID), FeedsPageRequestDto(page = page, size = size))
        return GetFeedsResponse.from(userID, feedPage)
    }
}
