package team_json_delivery.sns_b.domain.post.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team_json_delivery.sns_b.domain.post.exception.NotFoundPostException
import team_json_delivery.sns_b.domain.post.model.command.ModifyPostCommand
import team_json_delivery.sns_b.domain.post.repository.PostRepository

@Service
@Transactional
class ModifyPostService(
    private val postRepository: PostRepository
) {
    fun modify(postId: Long, command: ModifyPostCommand) {
        val post = postRepository.findByIdOrNull(postId) ?: throw NotFoundPostException()

        post.apply {
            content = command.content
        }
    }
}
