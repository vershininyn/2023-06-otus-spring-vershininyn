package ru.otus.spring.service.impl;

import ru.otus.spring.service.QuestionConsoleLogService;
import ru.otus.spring.service.QuestionLoaderService;

//TODO: Now log destination is console by default
public class QuestionConsoleLogServiceImpl implements QuestionConsoleLogService {
    private QuestionLoaderService qLoader;

    public QuestionConsoleLogServiceImpl(QuestionLoaderService qLoader) {
        this.qLoader = qLoader;
    }

    @Override
    public void logAllQuestions() {
        qLoader.getAllQuestions().forEach(System.out::println);
    }
}
