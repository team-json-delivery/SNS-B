package team_json_delivery.sns_b.global.exception

enum class ErrorCode(
    val code: String,
    val message: String
) {
    NOT_FOUND_POST("NOT_FOUND_POST", "게시글이 존재하지 않습니다."),
}
