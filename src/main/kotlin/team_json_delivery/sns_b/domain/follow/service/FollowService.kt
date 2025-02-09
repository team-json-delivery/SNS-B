package team_json_delivery.sns_b.domain.follow.service

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team_json_delivery.sns_b.domain.follow.domain.Follow
import team_json_delivery.sns_b.domain.follow.domain.vo.UserID
import team_json_delivery.sns_b.domain.follow.exception.DuplicatedFollowException
import team_json_delivery.sns_b.domain.follow.exception.SelfFollowNotAllowedException
import team_json_delivery.sns_b.domain.follow.repository.FollowRepository
import kotlin.jvm.Throws

@Service
@Transactional
class FollowService(
    val repository: FollowRepository,
) {
    @Throws(DuplicatedFollowException::class, SelfFollowNotAllowedException::class)
    fun follow(
        follower: UserID,
        followee: UserID,
    ) {
        if (follower == followee) {
            throw SelfFollowNotAllowedException()
        }

        try {
            repository.save(Follow(follower = follower, followee = followee))
        } catch (ex: DataIntegrityViolationException) {
            throw DuplicatedFollowException()
        }
    }
}
