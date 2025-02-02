package team_json_delivery.sns_b.global.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import team_json_delivery.sns_b.domain.post.exception.NotFoundUserException
import team_json_delivery.sns_b.global.model.response.WebResponse

@RestControllerAdvice
class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(
        value = [
            BusinessException::class,
            NotFoundUserException::class
        ]
    )
    fun badRequest(e: BusinessException): WebResponse<Any> {
        return WebResponse.error(e.errorCode)
    }
}
