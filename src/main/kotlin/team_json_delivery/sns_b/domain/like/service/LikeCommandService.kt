package team_json_delivery.sns_b.domain.like.service

import org.springframework.stereotype.Service
import team_json_delivery.sns_b.domain.like.domain.Like
import team_json_delivery.sns_b.domain.like.model.command.CreateLikedPostCommand
import team_json_delivery.sns_b.domain.like.model.command.DeleteLikedPostCommand
import team_json_delivery.sns_b.domain.like.repository.LikeRepository

@Service
class LikeCommandService(
    private val likeRepository: LikeRepository
) {
    fun like(createLikedPostCommand: CreateLikedPostCommand): Like {
        return likeRepository.save(
            Like(
                userId = createLikedPostCommand.userId,
                postId = createLikedPostCommand.postId
            )
        )
    }

    fun cancelLike(deleteLikedPostCommand: DeleteLikedPostCommand) {
        likeRepository.findByUserIdAndPostId(deleteLikedPostCommand.userId, deleteLikedPostCommand.postId)?.let {
            likeRepository.delete(it)
        }
    }
}