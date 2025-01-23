package team_json_delivery.sns_b.domain.post.controller

import io.kotest.core.spec.style.DescribeSpec

import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import team_json_delivery.sns_b.domain.post.model.dto.PostDto
import team_json_delivery.sns_b.domain.post.service.GetPostService

@WebMvcTest(GetPostController::class)
class GetPostControllerTest(
    private val mockMvc: MockMvc,
    @MockitoBean
    private val getPostService: GetPostService
) : DescribeSpec({
    describe("게시글 조회 API") {
        it("200 OK. 게시글을 조회한다.") {
            given(getPostService.getPost(any()))
                .willReturn(
                    PostDto(
                        id = 1,
                        content = "안녕하세요",
                    )
                )

            mockMvc.perform(get("/api/v1/posts/1"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.data.id").value("1"))
                .andExpect(jsonPath("$.data.content").value("안녕하세요"))
        }
    }
})
