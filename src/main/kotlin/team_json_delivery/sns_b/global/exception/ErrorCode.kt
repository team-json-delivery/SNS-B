package team_json_delivery.sns_b.global.exception

enum class ErrorCode(
    val code: String,
    val message: String,
) {
    NOT_FOUND_POST("NOT_FOUND_POST", "게시글이 존재하지 않습니다."),
    DUPLICATED_DATA("DUPLICATED_DATA", "이미 존재하는 데이터입니다."),
    SELF_FOLLOW_NOT_ALLOWED("SELF_FOLLOW_NOT_ALLOWED", "자기 자신을 팔로우할 수 없습니다."),
}
