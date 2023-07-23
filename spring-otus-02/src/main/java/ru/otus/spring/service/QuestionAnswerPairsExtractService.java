package ru.otus.spring.service;

import ru.otus.spring.domain.student.StudentQuestionAnswerPair;

import java.util.Map;

public interface QuestionAnswerPairsExtractService {
    Map<Long, StudentQuestionAnswerPair> getAllQuestionAnswerPairs() throws NullPointerException;

    Map<Long, StudentQuestionAnswerPair> getAllOptionalQuestionAnswerPairs() throws NullPointerException;

    Map<Long, StudentQuestionAnswerPair> getAllFreeQuestionAnswerPairs() throws NullPointerException;
}
