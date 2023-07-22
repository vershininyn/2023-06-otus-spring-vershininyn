package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.qa.TestAnswer;
import ru.otus.spring.domain.student.StudentQuestionAnswerPair;
import ru.otus.spring.service.QuestionAnswerPairsVerifyService;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class QuestionAnswerPairsVerifyServiceImpl implements QuestionAnswerPairsVerifyService {
    private double studentIsSuccessThresholdAtPercent = 0.0;

    public QuestionAnswerPairsVerifyServiceImpl(double studentIsSuccessThresholdAtPercent) {
        this.studentIsSuccessThresholdAtPercent = studentIsSuccessThresholdAtPercent;
    }

    @Override
    public boolean isStudentSuccessfullyPassTheTest(Map<Long, StudentQuestionAnswerPair> actualQuestionAnswerPairs,
                                                    Map<Long, StudentQuestionAnswerPair> trueQuestionAnswersPairs) throws Exception {
        Objects.requireNonNull(actualQuestionAnswerPairs, "The actual map cannot be NULL");
        Objects.requireNonNull(trueQuestionAnswersPairs, "The true map cannot be NULL");

        if (actualQuestionAnswerPairs.isEmpty()) {
            throw new IllegalArgumentException("The actual map cannot be empty");
        }

        if (trueQuestionAnswersPairs.isEmpty()) {
            throw new IllegalArgumentException("The true map cannot be empty");
        }

        if (trueQuestionAnswersPairs.size() != actualQuestionAnswerPairs.size()) {
            throw new IllegalArgumentException("The actual map and the true map size cannot be not equals");
        }

        final AtomicReference<Double> numerator = new AtomicReference<>(0.0);

        trueQuestionAnswersPairs.keySet().forEach(key -> {
            StudentQuestionAnswerPair actualStudentQuestionAnswerPair = actualQuestionAnswerPairs.get(key),
                    trueStudentQuestionAnswerPair = trueQuestionAnswersPairs.get(key);

            TestAnswer actualAnswer = actualStudentQuestionAnswerPair.getAnswer(),
                    trueAnswer = trueStudentQuestionAnswerPair.getAnswer();

            if (actualAnswer.equals(trueAnswer)) {
                numerator.getAndSet(numerator.get() + 1.0);
            }
        });

        double denominatorQuestionAnswerPair = trueQuestionAnswersPairs.size(),
                numeratorQuestionAnswerPair = numerator.get(),
                decimalResult = ((numeratorQuestionAnswerPair) / (denominatorQuestionAnswerPair)) * 100.0;

        return decimalResult >= studentIsSuccessThresholdAtPercent;
    }
}
