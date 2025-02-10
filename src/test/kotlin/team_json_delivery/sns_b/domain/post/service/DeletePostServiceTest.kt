package team_json_delivery.sns_b.domain.post.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import team_json_delivery.sns_b.domain.post.domain.Post
import team_json_delivery.sns_b.domain.post.exception.NotFoundPostException
import team_json_delivery.sns_b.domain.post.repository.PostRepository

@DataJpaTest
@Import(DeletePostService::class)
class DeletePostServiceTest(
    private val postRepository: PostRepository,
    private val deletePostService: DeletePostService,
) : DescribeSpec({
    describe("delete 메서드는") {
        context("존재하는 글을 삭제하면") {
            val fixture = postRepository.save(
                Post(
                    content = "안녕하세요",
                    likeCount = 10,
                )
            )

            it("게시글을 삭제한다") {
                deletePostService.delete(fixture.id)
            }
        }
        context("존재하는 글이 아니면") {
            it("NotFoundPostException 예외를 던진다") {
                shouldThrow<NotFoundPostException> {
                    deletePostService.delete(999)
                }
            }
        }
    }
})
