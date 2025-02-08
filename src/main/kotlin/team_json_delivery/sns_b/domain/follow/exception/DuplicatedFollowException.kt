package team_json_delivery.sns_b.domain.follow.exception

import team_json_delivery.sns_b.global.exception.BusinessException
import team_json_delivery.sns_b.global.exception.ErrorCode

class DuplicatedFollowException : BusinessException(ErrorCode.DUPLICATED_FOLLOW)
