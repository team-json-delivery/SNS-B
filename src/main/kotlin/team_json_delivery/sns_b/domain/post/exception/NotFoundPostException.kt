package team_json_delivery.sns_b.domain.post.exception

import team_json_delivery.sns_b.global.exception.BusinessException
import team_json_delivery.sns_b.global.exception.ErrorCode

class NotFoundPostException : BusinessException(ErrorCode.NOT_FOUND_POST)
