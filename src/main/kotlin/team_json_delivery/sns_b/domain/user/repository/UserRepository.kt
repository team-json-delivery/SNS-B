package team_json_delivery.sns_b.domain.user.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import team_json_delivery.sns_b.domain.user.domain.User

interface UserRepository: JpaRepository<User, Long> {
    fun findByUserName(userName: String): User?
}