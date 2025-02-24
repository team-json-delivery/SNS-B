package team_json_delivery.sns_b.domain.post.model.dto

import team_json_delivery.sns_b.domain.post.domain.Post

data class PostDto(
    val id: Long,
    val content: String,
) {
    companion object {
        fun from(entity: Post): PostDto {
            return PostDto(
                id = entity.id,
                content = entity.content,
            )
        }
    }
}
