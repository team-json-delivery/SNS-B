package team_json_delivery.sns_b.domain.user.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import team_json_delivery.sns_b.domain.user.model.command.CreateUserCommand
import team_json_delivery.sns_b.domain.user.repository.UserRepository

@Service
class UserCommandService(
    private val userRepository: UserRepository
) {
    fun create(createUserCommand: CreateUserCommand): Long {
        userRepository.findByIdOrNull(createUserCommand.id)
            ?: throw IllegalArgumentException("User with id ${createUserCommand.id} already exists")

        val user = userRepository.save(createUserCommand.toEntity())
        return user.id
    }

    fun update(createUserCommand: CreateUserCommand): Long {
        val user = userRepository.findByIdOrNull(createUserCommand.id)
            ?: throw IllegalArgumentException("User with id ${createUserCommand.id} does not exist")

        user.userName = createUserCommand.userName
        userRepository.save(user)

        return user.id
    }

    fun delete(userId: Long) {
        userRepository.deleteById(userId)
    }

}