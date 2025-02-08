package team_json_delivery.sns_b.global.exception

import org.springframework.http.HttpStatusCode
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(BusinessException::class)
    fun businessExceptionHandler(
        ex: BusinessException,
        request: WebRequest,
    ): ProblemDetail =
        createProblemDetail(
            ex = ex,
            status = ex.errorCode.status,
            defaultDetail = ex.errorCode.message,
            detailMessageCode = ex.errorCode.code,
            detailMessageArguments = null,
            request = request,
        )

    @ExceptionHandler(Exception::class)
    fun exceptionHandler(
        ex: RuntimeException,
        request: WebRequest,
    ): ProblemDetail {
        val errorCode = ErrorCode.INTERNAL_SERVER_ERROR
        return createProblemDetail(
            ex = ex,
            status = errorCode.status,
            defaultDetail = errorCode.message,
            detailMessageCode = errorCode.code,
            detailMessageArguments = null,
            request = request,
        )
    }

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
            problemDetail.setProperty(ERROR_CODE, ex.errorCode.code)
        }
        return problemDetail
    }

    companion object {
        const val ERROR_CODE = "errorCode"
    }
}
