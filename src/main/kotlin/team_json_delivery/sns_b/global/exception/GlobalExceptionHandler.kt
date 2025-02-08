package team_json_delivery.sns_b.global.exception

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import team_json_delivery.sns_b.domain.post.exception.NotFoundPostException
import java.lang.Exception

@RestControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(NotFoundPostException::class)
    fun badRequest(
        ex: NotFoundPostException,
        request: WebRequest,
    ): ProblemDetail =
        createProblemDetail(
            ex = ex,
            status = HttpStatus.BAD_REQUEST,
            defaultDetail = ex.errorCode.message,
            detailMessageCode = ex.errorCode.code,
            detailMessageArguments = null,
            request = request,
        )

    override fun createProblemDetail(
        ex: Exception,
        status: HttpStatusCode,
        defaultDetail: String,
        detailMessageCode: String?,
        detailMessageArguments: Array<out Any>?,
        request: WebRequest,
    ): ProblemDetail {
        val problemDetail =
            super.createProblemDetail(
                ex,
                status,
                defaultDetail,
                detailMessageCode,
                detailMessageArguments,
                request,
            )

        if (ex is BusinessException) {
            problemDetail.setProperty("errorCode", ex.errorCode.code)
        }
        return problemDetail
    }
}
