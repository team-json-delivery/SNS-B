package team_json_delivery.sns_b.domain.user.model.command

import org.springframework.data.jpa.domain.AbstractPersistable_.id
import team_json_delivery.sns_b.domain.user.domain.User

data class ModifyUserCommand(
    val id: Long,
    val userName: String
)