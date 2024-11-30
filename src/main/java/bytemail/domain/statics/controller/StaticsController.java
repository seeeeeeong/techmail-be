package bytemail.domain.statics.controller;

import bytemail.domain.statics.dto.SentQuestionResultResDto;
import bytemail.domain.statics.dto.TodayRegisteredUserCountResDto;
import bytemail.domain.statics.service.StaticsService;
import bytemail.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StaticsController {

    private final StaticsService staticsService;

    @GetMapping("/statics/users")
    public ApiResponse<TodayRegisteredUserCountResDto> getTodayRegisteredUserCount() {
        return ApiResponse.success(staticsService.getTodayRegisteredUserCount());
    }

    @GetMapping("/statics/users-question")
    public ApiResponse<SentQuestionResultResDto> getSentQuestionResult() {
        return ApiResponse.success(staticsService.getSentQuestionResult());
    }

}
