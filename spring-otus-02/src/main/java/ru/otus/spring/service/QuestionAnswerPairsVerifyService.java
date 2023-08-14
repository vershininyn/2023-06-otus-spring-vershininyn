package ru.otus.spring.service;

import ru.otus.spring.domain.student.StudentQuestionAnswerPair;

import java.util.Map;

public interface QuestionAnswerPairsVerifyService {
    boolean isStudentSuccessfullyPassTheTest(Map<Long, StudentQuestionAnswerPair> actualQuestionAnswerPairs, Map<Long, StudentQuestionAnswerPair> trueQuestionAnswersPairs);
}
