package ru.otus.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.service.QuestionAnswerPairsVerifyService;
import ru.otus.spring.service.impl.QuestionAnswerPairsVerifyServiceImpl;

@Configuration
@PropertySource(value = {"classpath:application.properties"})
@ComponentScan
public class QuestionAnswerPairsVerifyServiceConfiguration {
    @Bean
    public QuestionAnswerPairsVerifyService
    questionAnswerPairsVerifyServiceImpl(@Value("${tests.students.success_threshold}") Double successThreshold) {
        return new QuestionAnswerPairsVerifyServiceImpl(successThreshold);
    }
}
