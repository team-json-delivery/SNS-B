package team_json_delivery.sns_b.domain.post.controller

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.DescribeSpec

import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import team_json_delivery.sns_b.domain.post.exception.NotFoundPostException
import team_json_delivery.sns_b.domain.post.model.dto.PostDto
import team_json_delivery.sns_b.domain.post.model.request.CreatePostRequest
import team_json_delivery.sns_b.domain.post.model.request.ModifyPostRequest
import team_json_delivery.sns_b.domain.post.service.CreatePostService
import team_json_delivery.sns_b.domain.post.service.DeletePostService
import team_json_delivery.sns_b.domain.post.service.GetPostService
import team_json_delivery.sns_b.domain.post.service.ModifyPostService
import team_json_delivery.sns_b.global.exception.GlobalExceptionHandler

@WebMvcTest(PostController::class)
@Import(GlobalExceptionHandler::class)
class PostControllerTest(
    private val mockMvc: MockMvc,
    @MockitoBean
    private val getPostService: GetPostService,
    @MockitoBean
    private val createPostService: CreatePostService,
    @MockitoBean
    private val modifyPostService: ModifyPostService,
    @MockitoBean
    private val deletePostService: DeletePostService,
    private val objectMapper: ObjectMapper,
) : DescribeSpec({
    describe("게시글 생성 API") {
        it("200 OK. 게시글을 생성한다.") {
            given(createPostService.create(any()))
                .willReturn(1)

            mockMvc.perform(
                post("/api/v1/posts")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        objectMapper.writeValueAsString(
                            CreatePostRequest("안녕하세요")
                        )
                    )
            )
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.data.id").value(1))
        }
    }

    describe("게시글 수정 API") {
        context("존재하는 게시글인 경우") {
            it("200 OK. 게시글을 수정한다.") {
                mockMvc.perform(
                    put("/api/v1/posts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                            objectMapper.writeValueAsString(
                                ModifyPostRequest("안녕하세요")
                            )
                        )
                )
                    .andExpect(status().isOk)
                    .andExpect(jsonPath("$.data.id").value(1))
            }
        }

        context("존재하지 않는 게시글인 경우") {
            it("404  NotFound. 게시글 수정에 실패한다.") {
                given(modifyPostService.modify(any(), any()))
                    .willThrow(NotFoundPostException())

                mockMvc.perform(
                    put("/api/v1/posts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                            objectMapper.writeValueAsString(
                                ModifyPostRequest("안녕하세요")
                            )
                        )
                )
                    .andExpect(status().isNotFound)
            }
        }
    }

    describe("게시글 삭제 API") {
        context("존재하는 게시글인 경우") {
            it("200 OK. 게시글을 삭제한다.") {
                mockMvc.perform(delete("/api/v1/posts/1"))
                    .andExpect(status().isOk)
            }
        }

        context("존재하지 않는 게시글인 경우") {
            given(deletePostService.delete(any()))
                .willThrow(NotFoundPostException())

            it("404 NotFound. 게시글 삭제에 실패한다.") {
                mockMvc.perform(delete("/api/v1/posts/1"))
                    .andExpect(status().isNotFound)
            }
        }
    }

    describe("게시글 조회 API") {
        context("존재하는 게시글인 경우") {
            it("200 OK. 게시글을 조회한다.") {
                given(getPostService.getPost(any()))
                    .willReturn(
                        PostDto(
                            id = 1,
                            likeCount = 10,
                            content = "안녕하세요",
                        )
                    )

                mockMvc.perform(get("/api/v1/posts/1"))
                    .andExpect(status().isOk)
                    .andExpect(jsonPath("$.data.id").value("1"))
                    .andExpect(jsonPath("$.data.content").value("안녕하세요"))
                    .andExpect(jsonPath("$.data.likeCount").value(10))
            }
        }

        context("존재하지 않는 게시글인 경우") {
            it("404  NotFound. 게시글 조회에 실패한다.") {
                given(getPostService.getPost(any()))
                    .willThrow(NotFoundPostException())

                mockMvc.perform(get("/api/v1/posts/1"))
                    .andExpect(status().isNotFound)
            }
        }
    }
})
