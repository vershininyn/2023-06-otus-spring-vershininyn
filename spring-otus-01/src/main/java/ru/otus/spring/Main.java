package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.QuestionsExtractService;
import ru.otus.spring.service.QuestionsLogService;
import ru.otus.spring.service.impl.QuestionsConsoleLogServiceImpl;
import ru.otus.spring.service.impl.QuestionsExtractServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-context.xml");

        QuestionsExtractService extractService = context.getBean("questionsExtractService", QuestionsExtractServiceImpl.class);

        List<Question> questionsList = extractService.getAllQuestions();

        QuestionsLogService logService = context.getBean("questionsLogService", QuestionsConsoleLogServiceImpl.class);

        logService.logAllQuestions(questionsList);
    }
}
