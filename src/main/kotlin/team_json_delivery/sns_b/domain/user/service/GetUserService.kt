package team_json_delivery.sns_b.domain.user.service

import org.springframework.stereotype.Service
import team_json_delivery.sns_b.domain.post.exception.NotFoundUserException
import team_json_delivery.sns_b.domain.user.domain.User
import team_json_delivery.sns_b.domain.user.repository.UserRepository

@Service
class GetUserService(
    private val userRepository: UserRepository
) {
    fun getUser(userId: Long): User {
        return userRepository.findById(userId)
            .orElseThrow { NotFoundUserException() }
    }
}