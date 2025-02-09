package team_json_delivery.sns_b.domain.user.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "user")
class User(
    @Id
    val id: Long,
    val userName: String,
){
    fun copy(userName: String): User {
        return User(
            id = this.id,
            userName = userName
        )
    }
}