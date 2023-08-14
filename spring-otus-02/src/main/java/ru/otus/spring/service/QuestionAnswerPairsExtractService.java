package ru.otus.spring.service;

import ru.otus.spring.domain.student.StudentQuestionAnswerPair;

import java.util.Map;

public interface QuestionAnswerPairsExtractService {
    Map<Long, StudentQuestionAnswerPair> getAllQuestionAnswerPairs();

    Map<Long, StudentQuestionAnswerPair> getAllOptionalQuestionAnswerPairs();

    Map<Long, StudentQuestionAnswerPair> getAllFreeQuestionAnswerPairs();
}
