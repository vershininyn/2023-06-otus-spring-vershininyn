package ru.otus.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.service.QuestionAnswerPairsFromResourceLoaderService;
import ru.otus.spring.service.impl.QuestionAnswerPairsFromResourceLoaderServiceImpl;

@Configuration
@PropertySource(value = {"classpath:application.properties"})
@ComponentScan
public class QuestionAnswerPairsFromResourceLoaderServiceConfiguration {
    @Bean
    public QuestionAnswerPairsFromResourceLoaderService
    questionAnswerPairsFromResourceLoaderServiceImpl(@Value("${tests.students.csv_resource_filename}") String jsonFilename) {
        return new QuestionAnswerPairsFromResourceLoaderServiceImpl(jsonFilename);
    }
}
