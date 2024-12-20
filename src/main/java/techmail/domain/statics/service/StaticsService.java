package techmail.domain.statics.service;

import techmail.domain.statics.dto.SentQuestionResultResDto;
import techmail.domain.statics.dto.TodayRegisteredUserCountResDto;
import techmail.domain.userquestion.entity.UserQuestion;
import techmail.domain.userquestion.repository.UserQuestionRepository;
import techmail.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StaticsService {

    private final UserRepository userRepository;
    private final UserQuestionRepository userQuestionRepository;

    public TodayRegisteredUserCountResDto getTodayRegisteredUserCount() {
        List<String> emailList = userRepository.selectEmailList();
        return new TodayRegisteredUserCountResDto((long) emailList.size());
    }

    public SentQuestionResultResDto getSentQuestionResult() {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.plusDays(1).atStartOfDay().minusNanos(1);
        List<UserQuestion> userQuestions = userQuestionRepository.findUserQuestionByCreatedAtBetween(startOfDay, endOfDay);

        Map<Boolean, Long> result = userQuestions.stream()
                .collect(Collectors.partitioningBy(UserQuestion::isSuccess, Collectors.counting()));

        return new SentQuestionResultResDto("sentQuestion", result.get(true), result.get(false));
    }

}
