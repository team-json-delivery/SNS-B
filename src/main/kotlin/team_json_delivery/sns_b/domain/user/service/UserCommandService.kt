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
        userRepository.findByIdOrNull(createUserCommand.id)
            ?: throw NotFoundUserException()

        val user = userRepository.save(createUserCommand.toEntity())
        return user.id
    }

    fun update(modifyUserCommand: ModifyUserCommand): Long {
        val user = userRepository.findByIdOrNull(modifyUserCommand.id)
            ?: throw NotFoundUserException()

        user.userName = modifyUserCommand.userName
        userRepository.save(user)

        return user.id
    }

    fun delete(userId: Long) {
        userRepository.findByIdOrNull(userId) ?: throw NotFoundUserException()

        userRepository.deleteById(userId)
    }

}