package team_json_delivery.sns_b.domain.follow.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team_json_delivery.sns_b.domain.follow.domain.Follow
import team_json_delivery.sns_b.domain.follow.domain.vo.UserID

@Service
@Transactional(readOnly = true)
class ReadFollowService {
    fun getFollowers(user: UserID): List<Follow> {
        // 팔로워 목록 조회 로직
        return listOf()
    }

    fun getFollowings(user: UserID): List<Follow> {
        // 팔로잉 목록 조회 로직
        return listOf()
    }
}
