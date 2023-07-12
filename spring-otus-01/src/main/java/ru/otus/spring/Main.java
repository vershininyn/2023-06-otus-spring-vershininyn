package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.QuestionServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-context.xml");

        QuestionServiceImpl service = context.getBean("questionService", QuestionServiceImpl.class);

        List<Question> qList = service.getAllQuestions();

        qList.forEach(System.out::println);
    }
}
