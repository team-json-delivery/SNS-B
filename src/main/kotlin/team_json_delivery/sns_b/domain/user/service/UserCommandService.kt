package team_json_delivery.sns_b.domain.user.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import team_json_delivery.sns_b.domain.post.exception.NotFoundUserException
import team_json_delivery.sns_b.domain.user.model.command.CreateUserCommand
import team_json_delivery.sns_b.domain.user.model.command.ModifyUserCommand
import team_json_delivery.sns_b.domain.user.repository.UserRepository

@Service
class UserCommandService(
    private val userRepository: UserRepository
) {
    fun create(createUserCommand: CreateUserCommand): Long {
        userRepository.findByUserName(createUserCommand.userName)
            ?: throw NotFoundUserException()

        val user = userRepository.save(createUserCommand.toEntity())
        return user.id
    }

    fun update(modifyUserCommand: ModifyUserCommand): Long {
        val user = userRepository.findByIdOrNull(modifyUserCommand.id)
            ?: throw NotFoundUserException()
        userRepository.save(user.copy(modifyUserCommand.userName))
        return user.id
    }

    fun delete(userId: Long) {
        val user = userRepository.findByIdOrNull(userId) ?: throw NotFoundUserException()

        userRepository.delete(user)
    }

}