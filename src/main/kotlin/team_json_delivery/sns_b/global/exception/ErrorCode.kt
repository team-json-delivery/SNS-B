package team_json_delivery.sns_b.global.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val status: HttpStatus,
    val code: String,
    val message: String,
) {
    NOT_FOUND_POST(HttpStatus.NOT_FOUND, "NOT_FOUND_POST", "게시글이 존재하지 않습니다."),
    NOT_FOUND_FOLLOW(HttpStatus.NOT_FOUND, "NOT_FOUND_FOLLOW", "팔로우가 존재하지 않습니다."),
    DUPLICATED_FOLLOW(HttpStatus.CONFLICT, "DUPLICATED_FOLLOW", "이미 팔로우 되어 있습니다."),
    SELF_FOLLOW_NOT_ALLOWED(HttpStatus.UNPROCESSABLE_ENTITY, "SELF_FOLLOW_NOT_ALLOWED", "자기 자신을 팔로우할 수 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", "서버 에러 발생하였습니다."),
}
