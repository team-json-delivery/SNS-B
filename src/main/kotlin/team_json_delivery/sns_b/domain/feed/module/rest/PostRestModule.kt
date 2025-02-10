package team_json_delivery.sns_b.domain.feed.module.rest

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import team_json_delivery.sns_b.domain.feed.domain.vo.PostID
import team_json_delivery.sns_b.domain.feed.domain.vo.UserID

@Component
class PostRestModule(
    val client: WebClient,
) {
    fun getPostIdFor(followee: UserID): List<PostID> {
        TODO()
    }
}
