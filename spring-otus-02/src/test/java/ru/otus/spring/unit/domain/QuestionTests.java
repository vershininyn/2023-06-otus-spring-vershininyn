package ru.otus.spring.unit.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.mockito.MockSettings;
import org.mockito.exceptions.base.MockitoException;
import ru.otus.spring.domain.qa.QuestionType;
import ru.otus.spring.domain.qa.TestQuestion;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.RETURNS_MOCKS;

public class QuestionTests {
    @ParameterizedTest
    @CsvSource(value = {"free;100.0", "optional;100.0"}, delimiter = ';')
    public void shouldHaveCorrectAllArgsConstructor(final QuestionType type, final String questionStr) {
        final TestQuestion question = getWithSettingsQuestionTwoArgs(type, questionStr);

        given(question.getQuestionString()).willReturn(questionStr);
        given(question.getType()).willReturn(type);
        given(question.isBlank()).willReturn(false);

        assertEquals(questionStr, question.getQuestionString());
        assertEquals(type, question.getType());
        assertFalse(question.isBlank());

        verify(question, times(1)).getQuestionString();
        verify(question, times(1)).getType();
        verify(question, times(1)).isBlank();

        assertDoesNotThrow(() -> question.getQuestionString());
        assertDoesNotThrow(() -> question.getType());
        assertDoesNotThrow(() -> question.isBlank());
    }

    @ParameterizedTest
    @EmptySource
    public void shouldHaveCorrectNoArgsConstructor(final String answerStr) {
        final TestQuestion question = getWithSettingsQuestionNoArgs();

        given(question.getQuestionString()).willReturn(answerStr);
        given(question.isBlank()).willReturn(true);

        assertEquals(answerStr, question.getQuestionString());
        assertTrue(question.isBlank());

        verify(question, times(1)).getQuestionString();
        verify(question, times(1)).isBlank();

        assertDoesNotThrow(() -> question.getQuestionString());
        assertDoesNotThrow(() -> question.isBlank());
    }

    @ParameterizedTest
    @CsvSource(value= {"free;''", "optional;''","free;' '", "optional;' '"}, nullValues={"NULL"}, delimiter = ';')
    public void shouldCorrectlyHandleExceptionWhenUsingSetMethodAndQuestionStringIsEmptyOrBlank(final QuestionType type, final String questionStr) {
        assertThatThrownBy(() -> {
            try {
                // check the set method
                getWithSettingsQuestionTwoArgs(type, questionStr);
            } catch (final MockitoException ex) {
                throw new IllegalArgumentException("Question string cannot be blank");
            }
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Question string cannot be blank");
    }
    
    @Test
    public void shouldBeBlank() {
        final TestQuestion question = getWithSettingsQuestionNoArgs();

        given(question.isBlank()).willReturn(true);

        assertTrue(question.isBlank());
        verify(question, times(1)).isBlank();
        assertDoesNotThrow(() -> question.isBlank());
    }

    @ParameterizedTest
    @CsvSource(value = {"free;2.0;Question:{type:free,question:2.0}","optional;2.0;Question:{type:optional,question:2.0}"}, delimiter = ';')
    public void shouldHaveCorrectlyToStringMethod(QuestionType type, String questionStr, String toStringCorrectResult) {
        final TestQuestion question = getWithSettingsQuestionTwoArgs(type, questionStr);

        given(question.getQuestionString()).willReturn(questionStr);
        given(question.isBlank()).willReturn(false);
        given(question.toString()).willReturn(toStringCorrectResult);
        given(question.getType()).willReturn(type);
        given(question.setQuestionString(questionStr)).willReturn(question);

        assertFalse(question.isBlank());
        assertEquals(question, question.setQuestionString(questionStr));
        assertEquals(questionStr, question.getQuestionString());
        assertEquals(toStringCorrectResult, question.toString());
        assertEquals(type, question.getType());

        verify(question, times(1)).isBlank();
        verify(question, times(1)).setQuestionString(questionStr);
        verify(question, times(1)).getQuestionString();
        verify(question, times(1)).getType();

        assertDoesNotThrow(() -> question.getType());
        assertDoesNotThrow(() -> question.isBlank());
        assertDoesNotThrow(() -> question.setQuestionString(questionStr));
        assertDoesNotThrow(() -> question.setQuestionType(type));
    }

    private static TestQuestion getWithSettingsQuestionTwoArgs(QuestionType arg0, String arg1) throws MockitoException {
        return mock(TestQuestion.class, getMocksWithSettings().useConstructor(arg0, arg1));
    }

    private static TestQuestion getWithSettingsQuestionNoArgs() throws MockitoException {
        return mock(TestQuestion.class, getMocksWithSettings().useConstructor());
    }

    private static MockSettings getMocksWithSettings() {
        return withSettings().defaultAnswer(RETURNS_MOCKS);
    }
}