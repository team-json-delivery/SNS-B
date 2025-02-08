package team_json_delivery.sns_b.domain.follow.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team_json_delivery.sns_b.domain.follow.domain.Follow
import team_json_delivery.sns_b.domain.follow.domain.vo.UserID
import team_json_delivery.sns_b.domain.follow.repository.FollowRepository

@Service
@Transactional(readOnly = true)
class ReadFollowService(
    val repository: FollowRepository,
) {
    fun findFollowersFor(user: UserID): List<Follow> = repository.findAllByFollowee(user.value)

    fun findFolloweesFor(user: UserID): List<Follow> = repository.findAllByFollower(user.value)
}
