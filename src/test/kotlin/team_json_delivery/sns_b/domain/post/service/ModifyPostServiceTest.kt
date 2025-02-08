package team_json_delivery.sns_b.domain.post.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.data.repository.findByIdOrNull
import team_json_delivery.sns_b.domain.post.domain.Post
import team_json_delivery.sns_b.domain.post.exception.NotFoundPostException
import team_json_delivery.sns_b.domain.post.model.command.ModifyPostCommand
import team_json_delivery.sns_b.domain.post.repository.PostRepository

@DataJpaTest
@Import(ModifyPostService::class)
class ModifyPostServiceTest(
    private val postRepository: PostRepository,
    private val modifyPostService: ModifyPostService,
) : DescribeSpec({
    describe("modify 메서드는") {
        context("존재하는 글을 수정하면") {
            val fixture = postRepository.save(
                Post(
                    content = "안녕하세요",
                    likeCount = 10,
                )
            )

            it("게시글을 수정한다") {
                modifyPostService.modify(
                    postId = fixture.id,
                    command = ModifyPostCommand(
                        content = "감사합니다"
                    )
                )

                val result = postRepository.findByIdOrNull(fixture.id)

                result!!.content shouldBe "감사합니다"
            }
        }
        context("존재하는 글이 아니면") {
            it("NotFoundPostException 예외를 던진다") {
                shouldThrow<NotFoundPostException> {
                    modifyPostService.modify(
                        postId = 999,
                        command = ModifyPostCommand(
                            content = "감사합니다"
                        )
                    )
                }
            }
        }
    }
})
