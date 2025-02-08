package team_json_delivery.sns_b.global.exception

import io.kotest.core.spec.style.DescribeSpec
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.isEqualTo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team_json_delivery.sns_b.domain.post.exception.NotFoundPostException
import team_json_delivery.sns_b.global.exception.GlobalExceptionHandler.Companion.ERROR_CODE

@WebMvcTest(GlobalExceptionHandlerTestController::class)
class GlobalExceptionHandlerTest(
    private val mockMvc: MockMvc,
) : DescribeSpec({

        describe("NotFoundPostException 호출") {
            it("호출") {
                val ex = NotFoundPostException()
                mockMvc
                    .perform(get("/notfoundpost"))
                    .andDo(print())
                    .andExpect(status().isEqualTo(ex.errorCode.status.value()))
                    .andExpect(jsonPath("$.$ERROR_CODE").value(ex.errorCode.code))
            }
        }

        describe("RuntimeException 호출") {
            it("ErrorCode.INTERNAL_SERVER_ERROR 호출") {
                val errorCode = ErrorCode.INTERNAL_SERVER_ERROR
                mockMvc
                    .perform(get("/exception"))
                    .andDo(print())
                    .andExpect(status().isEqualTo(errorCode.status.value()))
            }
        }

        describe("지원하지 않는 Method 형식 호출") {
            it("BadRequest 호출") {
                mockMvc
                    .perform(post("/notfoundpost"))
                    .andDo(print())
                    .andExpect(status().isMethodNotAllowed)
            }
        }
    })

@RestController
@RequestMapping
class GlobalExceptionHandlerTestController {
    @GetMapping("/notfoundpost")
    fun notfoundpost(): String = throw NotFoundPostException()

    @GetMapping("/exception")
    fun exception(): String = throw RuntimeException()
}
