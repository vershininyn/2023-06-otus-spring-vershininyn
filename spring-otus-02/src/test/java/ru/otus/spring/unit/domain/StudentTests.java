package ru.otus.spring.unit.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.mockito.MockSettings;
import org.mockito.exceptions.base.MockitoException;
import ru.otus.spring.domain.qa.QuestionType;
import ru.otus.spring.domain.qa.TestQuestion;
import ru.otus.spring.domain.student.Student;

import java.util.StringJoiner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class StudentTests {
    @ParameterizedTest
    @CsvSource(value = {"Ivan;Ivan"}, delimiter = ';')
    public void shouldHaveCorrectAllArgsConstructor(final String firstName, final String secondName) {
        final Student student = getWithSettingsQuestionTwoArgs(firstName, secondName);

        given(student.getFirstName()).willReturn(firstName);
        given(student.getSecondName()).willReturn(secondName);
        given(student.isBlank()).willReturn(false);

        assertEquals(firstName, student.getFirstName());
        assertEquals(secondName, student.getSecondName());
        assertFalse(student.isBlank());

        verify(student, times(1)).getFirstName();
        verify(student, times(1)).getSecondName();
        verify(student, times(1)).isBlank();

        assertDoesNotThrow(() -> student.getFirstName());
        assertDoesNotThrow(() -> student.getSecondName());
        assertDoesNotThrow(() -> student.isBlank());
    }

    @Test
    public void shouldHaveCorrectNoArgsConstructor() {
        final Student student = getWithSettingsQuestionNoArgs();

        given(student.getSecondName()).willReturn("");
        given(student.getFirstName()).willReturn("");
        given(student.isBlank()).willReturn(true);

        assertEquals("", student.getFirstName());
        assertEquals("", student.getSecondName());
        assertTrue(student.isBlank());

        verify(student, times(1)).getFirstName();
        verify(student, times(1)).getSecondName();
        verify(student, times(1)).isBlank();

        assertDoesNotThrow(() -> student.getFirstName());
        assertDoesNotThrow(() -> student.getSecondName());
        assertDoesNotThrow(() -> student.isBlank());
    }

    @ParameterizedTest
    @CsvSource(value = {"fistName;NULL", "NULL;secondName", "NULL;NULL"}, nullValues = {"NULL"}, delimiter = ';')
    public void shouldCorrectlyHandleExceptionWhenUsingAllArgsConstructorAndFirstSecondNamesIsNull(final String firstName, final String secondName) {
        assertThatThrownBy(() -> {
            try {
                // check the set method
                getWithSettingsQuestionTwoArgs(firstName, secondName);
            } catch (final MockitoException ex) {
                throw new IllegalArgumentException("The student first or second name cannot bu NULL");
            }
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("The student first or second name cannot bu NULL");
    }

    @Test
    public void shouldBeBlank() {
        final Student student = getWithSettingsQuestionNoArgs();

        given(student.isBlank()).willReturn(true);

        assertTrue(student.isBlank());
        verify(student, times(1)).isBlank();
        assertDoesNotThrow(() -> student.isBlank());
    }

    @ParameterizedTest
    @CsvSource(value = {"firstName;secondName;Student {firstName =firstName,secondName =secondName}"}, delimiter = ';')
    public void shouldHaveCorrectlyToStringMethod(final String firstName, final String secondName, final String toStringCorrectResult) {
        final Student student = getWithSettingsQuestionTwoArgs(firstName, secondName);

        given(student.getFirstName()).willReturn(firstName);
        given(student.getSecondName()).willReturn(secondName);
        given(student.isBlank()).willReturn(false);
        given(student.toString()).willReturn(toStringCorrectResult);

        assertFalse(student.isBlank());
        assertEquals(firstName, student.getFirstName());
        assertEquals(secondName, student.getSecondName());

        verify(student, times(1)).isBlank();
        verify(student, times(1)).getFirstName();
        verify(student, times(1)).getSecondName();

        assertDoesNotThrow(() -> student.isBlank());
        assertDoesNotThrow(() -> student.getFirstName());
        assertDoesNotThrow(() -> student.getSecondName());
    }

    private static Student getWithSettingsQuestionTwoArgs(String firstName, String secondName) throws MockitoException {
        return mock(Student.class, getMocksWithSettings().useConstructor(firstName, secondName));
    }

    private static Student getWithSettingsQuestionNoArgs() throws MockitoException {
        return mock(Student.class, getMocksWithSettings().useConstructor());
    }

    private static MockSettings getMocksWithSettings() {
        return withSettings().defaultAnswer(RETURNS_MOCKS);
    }
}
