package ru.otus.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.service.QuestionAnswerPairsVerifyService;
import ru.otus.spring.service.impl.QuestionAnswerPairsVerifyServiceImpl;

@Configuration
@PropertySource(value = {"classpath:application.properties"})
public class QuestionAnswerPairsVerifyServiceConfiguration {
    @Value("${tests.students.success_threshold}")
    private Double successThreshold;

    @Bean("questionAnswerPairsVerifyServiceImpl")
    public QuestionAnswerPairsVerifyService
    configureQuestionAnswerPairsVerifyService() {
        return new QuestionAnswerPairsVerifyServiceImpl(successThreshold);
    }
}
