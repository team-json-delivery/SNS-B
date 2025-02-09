package team_json_delivery.sns_b.domain.post.exception

import team_json_delivery.sns_b.global.exception.BusinessException
import team_json_delivery.sns_b.global.exception.ErrorCode

class NotFoundUserException : BusinessException(ErrorCode.NOT_FOUND_USER)
