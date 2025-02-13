package team_json_delivery.sns_b.domain.feed.model.dto

import org.junit.jupiter.api.Assertions.*

class FeedsPageRequestDtoTest {
    companion object {
        fun of(
            page: Int = 0,
            size: Int = Int.MAX_VALUE,
        ): FeedsPageRequestDto =
            FeedsPageRequestDto(
                page = page,
                size = size,
            )
    }
}
