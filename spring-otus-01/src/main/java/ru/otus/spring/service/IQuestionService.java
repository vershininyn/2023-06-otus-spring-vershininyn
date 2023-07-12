package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

import java.util.List;

public interface IQuestionService {
    List<Question> getAllQuestions();

    List<Question> getAllOptionalQuestions();

    List<Question> getAllFreeQuestions();
}
