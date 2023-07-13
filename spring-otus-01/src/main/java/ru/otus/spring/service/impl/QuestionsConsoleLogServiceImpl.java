package ru.otus.spring.service.impl;

import ru.otus.spring.domain.Question;
import ru.otus.spring.service.QuestionsLogService;

import java.util.List;

public class QuestionsConsoleLogServiceImpl implements QuestionsLogService {
    @Override
    public void logAllQuestions(List<Question> questionsList) {
        questionsList.forEach(System.out::println);
    }
}
