package team_json_delivery.sns_b.global.exception

enum class ErrorCode(
    val code: String,
    val message: String,
) {
    NOT_FOUND_POST("NOT_FOUND_POST", "게시글이 존재하지 않습니다."),
    NOT_FOUND_FOLLOW("NOT_FOUND_FOLLOW", "팔로우가 존재하지 않습니다."),
    DUPLICATED_FOLLOW("DUPLICATED_FOLLOW", "이미 팔로우 되어 있습니다."),
    SELF_FOLLOW_NOT_ALLOWED("SELF_FOLLOW_NOT_ALLOWED", "자기 자신을 팔로우할 수 없습니다."),
}
