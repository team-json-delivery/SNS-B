package team_json_delivery.sns_b.domain.user.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team_json_delivery.sns_b.domain.user.model.command.CreateUserCommand
import team_json_delivery.sns_b.domain.user.model.request.CreateUserRequest
import team_json_delivery.sns_b.domain.user.model.response.CreateUserResponse
import team_json_delivery.sns_b.domain.user.model.response.GetUserResponse
import team_json_delivery.sns_b.domain.user.service.CreateUserService
import team_json_delivery.sns_b.domain.user.service.GetUserService
import team_json_delivery.sns_b.global.model.response.WebResponse

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val createUserService: CreateUserService,
    private val getUserService: GetUserService
) {
    @Operation(summary = "유저 생성")
    @PostMapping
    fun createUser(request: CreateUserRequest): WebResponse<CreateUserResponse> {
        val userId = createUserService.create(
            CreateUserCommand(request.id, request.userName)
        )

        return WebResponse.success(
            CreateUserResponse(userId, request.userName)
        )
    }

    @Operation(summary = "유저 조회")
    @GetMapping("/{userId}")
    fun getUser(
        @Schema(description = "유저 번호", example = "123")
        @PathVariable userId: Long
    ): WebResponse<GetUserResponse> {
        val user = getUserService.getUser(userId)

        return WebResponse.success(
            GetUserResponse.from(user)
        )
    }
}