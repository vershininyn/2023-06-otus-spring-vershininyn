package ru.otus.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.service.QuestionAnswerPairsFromResourceLoaderService;
import ru.otus.spring.service.impl.QuestionAnswerPairsFromResourceLoaderServiceImpl;

@Configuration
@PropertySource(value = {"classpath:application.properties"})
public class QuestionAnswerPairsFromResourceLoaderServiceConfiguration {
    @Value("${tests.students.csv_resource_filename}")
    private String jsonFilename;

    @Bean("questionAnswerPairsFromResourceLoaderServiceImpl")
    public QuestionAnswerPairsFromResourceLoaderService
    configureQuestionAnswerPairsFromResourcesLoaderService() {
        return new QuestionAnswerPairsFromResourceLoaderServiceImpl(jsonFilename);
    }
}
