package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

import java.util.List;

public interface QuestionLoaderService {
    List<Question> getAllQuestions() throws NullPointerException;

    List<Question> getAllOptionalQuestions() throws NullPointerException;

    List<Question> getAllFreeQuestions() throws NullPointerException;
}
