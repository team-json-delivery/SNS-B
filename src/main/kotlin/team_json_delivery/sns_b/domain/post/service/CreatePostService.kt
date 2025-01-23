package team_json_delivery.sns_b.domain.post.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team_json_delivery.sns_b.domain.post.domain.Post
import team_json_delivery.sns_b.domain.post.model.command.CreatePostCommand
import team_json_delivery.sns_b.domain.post.repository.PostRepository

@Service
@Transactional
class CreatePostService(
    private val postRepository: PostRepository
) {
    fun create(command: CreatePostCommand): Long {
        val post = postRepository.save(
            Post(content = command.content)
        )

        return post.id
    }
}
