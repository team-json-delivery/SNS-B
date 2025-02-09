package team_json_delivery.sns_b.domain.follow.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team_json_delivery.sns_b.domain.follow.domain.UserWithFollowers
import team_json_delivery.sns_b.domain.follow.domain.UserWithFollowings
import team_json_delivery.sns_b.domain.follow.domain.vo.UserID

@Service
@Transactional(readOnly = true)
class ReadFollowService {
    fun readFollwers(user: UserID): UserWithFollowers = UserWithFollowers(user = user, followers = setOf())

    fun readFollowings(user: UserID): UserWithFollowings = UserWithFollowings(user = user, followings = setOf())
}
