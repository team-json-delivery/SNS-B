package team_json_delivery.sns_b.global.exception

import team_json_delivery.sns_b.global.exception.ErrorCode

open class BusinessException(val errorCode: ErrorCode) : RuntimeException(errorCode.message)
