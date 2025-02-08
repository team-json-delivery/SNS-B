package team_json_delivery.sns_b.global.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import team_json_delivery.sns_b.domain.post.exception.NotFoundPostException
import team_json_delivery.sns_b.global.model.response.WebResponse

@RestControllerAdvice
class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundPostException::class)
    fun badRequest(e: BusinessException): WebResponse<Any> {
        return WebResponse.error(e.errorCode)
    }
}
