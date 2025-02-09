package team_json_delivery.sns_b.domain.user.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import team_json_delivery.sns_b.domain.user.model.command.CreateUserCommand
import team_json_delivery.sns_b.domain.user.model.request.CreateUserRequest
import team_json_delivery.sns_b.domain.user.model.response.CreateUserResponse
import team_json_delivery.sns_b.domain.user.model.response.GetUserResponse
import team_json_delivery.sns_b.domain.user.service.UserCommandService
import team_json_delivery.sns_b.domain.user.service.UserQueryService
import team_json_delivery.sns_b.global.model.response.WebResponse

@RestController
@RequestMapping("/api/v1/users")
class UserCommandController(
    private val userCommandService: UserCommandService,
) {
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "유저 생성")
    @PostMapping
    fun createUser(request: CreateUserRequest): WebResponse<CreateUserResponse> {
        val userId = userCommandService.create(
            CreateUserCommand(request.id, request.userName)
        )

        return WebResponse.success(
            CreateUserResponse(userId, request.userName)
        )
    }

    @Operation(summary = "유저 수정")
    @PutMapping("/{userId}")
    fun updateUser(
        @Schema(description = "유저 번호", example = "123")
        @PathVariable userId: Long,
        request: CreateUserRequest
    ): WebResponse<CreateUserResponse> {
        val updatedUserId = userCommandService.update(
            CreateUserCommand(userId, request.userName)
        )

        return WebResponse.success(
            CreateUserResponse(updatedUserId, request.userName)
        )
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "유저 삭제")
    @DeleteMapping("/{userId}")
    fun deleteUser(
        @Schema(description = "유저 번호", example = "123")
        @PathVariable userId: Long
    ): WebResponse<Unit> {
        userCommandService.delete(userId)

        return WebResponse.success()
    }

}