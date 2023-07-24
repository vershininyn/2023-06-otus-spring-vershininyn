package ru.otus.spring.unit.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.MockSettings;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.domain.qa.TestAnswer;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AnswerTests {
    @ParameterizedTest
    @CsvSource(value = {"100.0"})
    public void answerObjShouldHaveCorrectAllArgsConstructor(final String answerStr) {
        final TestAnswer answer = getWithSettingsTestAnswerOneArgs(answerStr);

        given(answer.getAnswer()).willReturn(answerStr);
        given(answer.isBlank()).willReturn(false);

        assertEquals(answerStr, answer.getAnswer());
        assertFalse(answer.isBlank());

        verify(answer, times(1)).getAnswer();
        verify(answer, times(1)).isBlank();

        assertDoesNotThrow(() -> answer.getAnswer());
        assertDoesNotThrow(() -> answer.isBlank());
    }

    @ParameterizedTest
    @EmptySource
    public void answerObjShouldCorrectlyHandleExceptionWhenUsingAllArgsConstructorWhenAnswerStringIsEmpty(final String answerStr) {
        assertThatThrownBy(() -> {
            try {
                getWithSettingsTestAnswerOneArgs(answerStr);
            } catch (final MockitoException ex) {
                throw new IllegalArgumentException("Answer cannot be blank");
            }
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Answer cannot be blank");
    }

    @ParameterizedTest
    @NullSource
    public void answerObjShouldCorrectlyHandleExceptionWhenUsingAllArgsConstructorWhenAnswerStringIsNull(final String answerStr) {
        assertThatThrownBy(() -> {
            try {
                getWithSettingsTestAnswerOneArgs(answerStr);
            } catch (final MockitoException ex) {
                throw new NullPointerException("Answer cannot be NULL");
            }
        })
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("Answer cannot be NULL");
    }

    @ParameterizedTest
    @EmptySource
    public void answerObjShouldHaveCorrectNoArgsConstructor(final String answerStr) {
        final TestAnswer answer = getWithSettingsTestAnswerNoArgs();

        given(answer.getAnswer()).willReturn(answerStr);
        given(answer.isBlank()).willReturn(true);

        assertEquals(answerStr, answer.getAnswer());
        assertTrue(answer.isBlank());

        verify(answer, times(1)).getAnswer();
        verify(answer, times(1)).isBlank();

        assertDoesNotThrow(() -> answer.getAnswer());
        assertDoesNotThrow(() -> answer.isBlank());
    }

    @ParameterizedTest
    @EmptySource
    public void answerObjShouldCorrectlyHandleExceptionWhenUsingNoArgsConstructorWhenAnswerStringIsEmpty(final String answerStr) {
        assertThatThrownBy(() -> {
            try {
                getWithSettingsTestAnswerOneArgs(answerStr);
            } catch (final MockitoException ex) {
                throw new IllegalArgumentException("Answer cannot be blank");
            }
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Answer cannot be blank");
    }

    @ParameterizedTest
    @NullSource
    public void answerObjShouldCorrectlyHandleExceptionWhenUsingNoArgsConstructorWhenAnswerStringIsNull(final String answerStr) {
        assertThatThrownBy(() -> {
            try {
                getWithSettingsTestAnswerOneArgs(answerStr);
            } catch (final MockitoException ex) {
                throw new NullPointerException("Answer cannot be NULL");
            }
        })
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("Answer cannot be NULL");
    }

    @ParameterizedTest
    @CsvSource(value = {"100.0"})
    public void answerObjShouldHaveCorrectAnswerSetter(String answerStr) {
        final TestAnswer answer = getWithSettingsTestAnswerOneArgs(answerStr);

        given(answer.setAnswer(answerStr)).willReturn(answer);
        given(answer.getAnswer()).willReturn(answerStr);
        given(answer.isBlank()).willReturn(true);

        assertEquals(answer.setAnswer(answerStr), answer);
        assertEquals(answerStr, answer.getAnswer());
        assertTrue(answer.isBlank());

        verify(answer, times(1)).setAnswer(answerStr);
        verify(answer, times(1)).getAnswer();
        verify(answer, times(1)).isBlank();

        assertDoesNotThrow(() -> answer.getAnswer());
        assertDoesNotThrow(() -> answer.setAnswer(answerStr));
        assertDoesNotThrow(() -> answer.isBlank());
    }

    @Test
    public void answerObjShouldBeBlank() {
        final TestAnswer answer = getWithSettingsTestAnswerNoArgs();

        given(answer.isBlank()).willReturn(true);

        assertTrue(answer.isBlank());
        verify(answer, times(1)).isBlank();
        assertDoesNotThrow(() -> answer.isBlank());
    }

    @ParameterizedTest
    @CsvSource(value = {"2.0;Answer: {answer =2.0}"}, delimiter = ';')
    public void answerObjShouldHaveCorrectlyToStringMethod(String answerStr, String toStringCorrectResult) {
        final TestAnswer answer = getWithSettingsTestAnswerOneArgs(answerStr);

        given(answer.getAnswer()).willReturn(answerStr);
        given(answer.isBlank()).willReturn(false);
        given(answer.toString()).willReturn(toStringCorrectResult);

        assertFalse(answer.isBlank());
        assertEquals(answerStr, answer.getAnswer());
        assertEquals(toStringCorrectResult, answer.toString());

        verify(answer, times(1)).isBlank();
        verify(answer, times(1)).getAnswer();
        verify(answer, times(1)).getAnswer();

        assertDoesNotThrow(() -> answer.getAnswer());
        assertDoesNotThrow(() -> answer.setAnswer(answerStr));
        assertDoesNotThrow(() -> answer.isBlank());
    }

    private static final TestAnswer getWithSettingsTestAnswerOneArgs(String arg0) throws MockitoException {
        return mock(TestAnswer.class, getMocksWithSettings().useConstructor(arg0));
    }

    private static final TestAnswer getWithSettingsTestAnswerNoArgs() throws MockitoException {
        return mock(TestAnswer.class, getMocksWithSettings().useConstructor());
    }

    private static final MockSettings getMocksWithSettings() {
        return withSettings().defaultAnswer(RETURNS_MOCKS);
    }
}
