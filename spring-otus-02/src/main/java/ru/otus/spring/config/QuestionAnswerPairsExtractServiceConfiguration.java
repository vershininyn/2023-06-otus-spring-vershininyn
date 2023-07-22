package ru.otus.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.service.QuestionAnswerPairsExtractService;
import ru.otus.spring.service.QuestionAnswerPairsFromResourceLoaderService;
import ru.otus.spring.service.impl.QuestionAnswerPairsExtractServiceImpl;

@Configuration
public class QuestionAnswerPairsExtractServiceConfiguration {
    @Bean("questionAnswerPairsExtractServiceImpl")
    public QuestionAnswerPairsExtractService
    configureQuestionsExtractService(@Autowired QuestionAnswerPairsFromResourceLoaderService qLoader) throws Exception {
        return new QuestionAnswerPairsExtractServiceImpl(qLoader);
    }
}
