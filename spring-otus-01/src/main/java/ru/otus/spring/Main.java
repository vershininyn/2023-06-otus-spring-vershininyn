package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.service.impl.QuestionConsoleLogServiceImpl;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-context.xml");

        QuestionConsoleLogServiceImpl service = context.getBean("questionsLogService", QuestionConsoleLogServiceImpl.class);

        service.logAllQuestions();
    }
}
