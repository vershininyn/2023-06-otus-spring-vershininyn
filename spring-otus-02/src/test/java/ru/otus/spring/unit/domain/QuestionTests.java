package ru.otus.spring.unit.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.otus.spring.domain.qa.QuestionType;
import ru.otus.spring.domain.qa.TestQuestion;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionTests {
    @ParameterizedTest
    @CsvSource(value = {"'How much is 1.0+1.0?':optional", "'How much is 1.0+1.0?':free"}, delimiter = ':')
    public void questionObjShouldHaveCorrectConstructor(String questionStr, QuestionType questionType) {
        TestQuestion question = new TestQuestion(questionType, questionStr);

        assertEquals(questionType, question.getType());
        assertEquals(questionStr, question.getQuestionString());
        assertFalse(question.isBlank());
    }

    @ParameterizedTest
    @CsvSource(value = {"'':optional", "'':free"}, delimiter = ':')
    public void questionObjShouldBeEmpty(String questionStr, QuestionType questionType) {
        TestQuestion question = new TestQuestion(questionType, questionStr);

        assertTrue(question.isBlank());
        assertEquals(questionType, question.getType());
    }

    @ParameterizedTest
    @CsvSource(value = {"'How much is 1.0+1.0?';optional;'Question:{type:optional,question:How much is 1.0+1.0?}'",
                        "'How much is 1.0+1.0?';free;'Question:{type:free,question:How much is 1.0+1.0?}'"},
                        delimiter = ';')
    public void questionObjShouldHaveCorrectToStringMethod(String questionStr,
                                                           QuestionType questionType,
                                                           String toStringCorrectResult) {
        TestQuestion question = new TestQuestion(questionType, questionStr);

        assertFalse(question.isBlank());
        assertEquals(questionStr, question.getQuestionString());
        assertEquals(questionType, question.getType());
        assertTrue(question.toString().equals(toStringCorrectResult));
    }
}
