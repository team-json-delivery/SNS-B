package team_json_delivery.sns_b.domain.post.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import team_json_delivery.sns_b.domain.post.domain.Post
import team_json_delivery.sns_b.domain.post.exception.NotFoundPostException
import team_json_delivery.sns_b.domain.post.repository.PostRepository

@DataJpaTest
@Import(GetPostService::class)
class GetPostServiceTest(
    private val postRepository: PostRepository,
    private val getPostService: GetPostService
) : DescribeSpec({
    describe("getPost 메서드는") {
        context("존재하는 게시글 번호를 조회하면") {
            val fixture = postRepository.save(
                Post(
                    content = "안녕하세요"
                )
            )

            it("게시글을 반환한다") {
                val post = getPostService.getPost(fixture.id)

                post.content shouldBe fixture.content
            }
        }

        context("없는 게시글 번호를 조회하면") {
            it("NotFoundPostException 예외를 던진다") {
                shouldThrow<NotFoundPostException> {
                    getPostService.getPost(11)
                }
            }
        }
    }
})
