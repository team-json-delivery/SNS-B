package team_json_delivery.sns_b.domain.post.service

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import team_json_delivery.sns_b.domain.post.model.command.CreatePostCommand

@DataJpaTest
@Import(CreatePostService::class)
class CreatePostServiceTest(
    private val createPostService: CreatePostService,
) : DescribeSpec({
    describe("create 메서드는") {
        it("게시글을 생성한다") {
            val result = createPostService.create(
                CreatePostCommand("안녕하세요")
            )

            result shouldBe 1
        }
    }
})
