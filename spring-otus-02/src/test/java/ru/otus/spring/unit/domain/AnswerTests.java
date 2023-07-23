package ru.otus.spring.unit.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.otus.spring.domain.qa.TestAnswer;

import static org.junit.jupiter.api.Assertions.*;

public class AnswerTests {
    @ParameterizedTest
    @CsvSource(value = {"'100.0'"})
    public void answerObjShouldHaveCorrectAllArgsConstructor(String answerStr) {
        TestAnswer answer = new TestAnswer(answerStr);

        assertEquals(answerStr, answer.getAnswer());
        assertFalse(answer.isBlank());
    }

    @Test
    public void answerObjShouldHaveCorrectNoArgsConstructor() {
        TestAnswer answer = new TestAnswer();

        assertTrue(answer.isBlank());
    }

    @ParameterizedTest
    @CsvSource(value = {"100.0"})
    public void answerObjShouldHaveCorrectAnswerSetter(String answerStr) {
        TestAnswer answer = new TestAnswer();

        answer.setAnswer(answerStr);
        
        assertEquals(answerStr, answer.getAnswer());
        assertFalse(answer.isBlank());
    }

    @Test
    public void answerObjShouldBeBlank() {
        TestAnswer answer = new TestAnswer();

        assertTrue(answer.isBlank());
    }

    @ParameterizedTest
    @CsvSource(value = {"'How much is 1.0+1.0?';'Answer: {answer =How much is 1.0+1.0?}'"}, delimiter = ';')
    public void answerObjShouldHaveCorrectToStringMethod(String answerStr, String toStringCorrectResult) {
        TestAnswer answer = new TestAnswer(answerStr);

        assertFalse(answer.isBlank());
        assertEquals(answerStr, answer.getAnswer());
        assertTrue(answer.toString().equals(toStringCorrectResult));
    }
}
