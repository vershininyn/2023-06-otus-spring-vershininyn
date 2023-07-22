package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.service.QuestionAnswerPairsLogService;
import ru.otus.spring.service.impl.QuestionAnswerPairsConsoleLogServiceImpl;

@Configuration
public class QuestionAnswerPairsLogServiceConfiguration {
    @Bean("questionAnswerPairsConsoleLogServiceImpl")
    public QuestionAnswerPairsLogService configureQuestionAnswerLogService() {
        return new QuestionAnswerPairsConsoleLogServiceImpl();
    }
}
