package ru.otus.spring.unit.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockSettings;
import org.mockito.exceptions.base.MockitoException;
import ru.otus.spring.domain.student.Student;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class StudentTests {
    @ParameterizedTest
    @CsvSource(value = {"Ivan;Ivan;false", "Ivan;Ivan;true"}, delimiter = ';')
    public void shouldHaveCorrectAllArgsConstructor(final String firstName,
                                                    final String secondName,
                                                    boolean isStudentPassedTest) {
        final Student student = getWithSettingsQuestionTwoArgs(firstName, secondName, isStudentPassedTest);

        given(student.getFirstName()).willReturn(firstName);
        given(student.getSecondName()).willReturn(secondName);
        given(student.isPassedTest()).willReturn(isStudentPassedTest);
        given(student.isBlank()).willReturn(false);

        assertEquals(firstName, student.getFirstName());
        assertEquals(secondName, student.getSecondName());
        assertEquals(isStudentPassedTest, student.isPassedTest());
        assertFalse(student.isBlank());

        verify(student, times(1)).getFirstName();
        verify(student, times(1)).getSecondName();
        verify(student, times(1)).isPassedTest();
        verify(student, times(1)).isBlank();

        assertDoesNotThrow(() -> student.getFirstName());
        assertDoesNotThrow(() -> student.getSecondName());
        assertDoesNotThrow(() -> student.isPassedTest());
        assertDoesNotThrow(() -> student.isBlank());
    }

    @Test
    public void shouldHaveCorrectNoArgsConstructor() {
        final Student student = getWithSettingsQuestionNoArgs();

        given(student.getSecondName()).willReturn("");
        given(student.getFirstName()).willReturn("");
        given(student.isPassedTest()).willReturn(false);
        given(student.isBlank()).willReturn(true);

        assertEquals("", student.getFirstName());
        assertEquals("", student.getSecondName());
        assertFalse(student.isPassedTest());
        assertTrue(student.isBlank());

        verify(student, times(1)).getFirstName();
        verify(student, times(1)).getSecondName();
        verify(student, times(1)).isPassedTest();
        verify(student, times(1)).isBlank();

        assertDoesNotThrow(() -> student.getFirstName());
        assertDoesNotThrow(() -> student.getSecondName());
        assertDoesNotThrow(() -> student.isPassedTest());
        assertDoesNotThrow(() -> student.isBlank());
    }

    @ParameterizedTest
    @CsvSource(value = {"fistName;NULL;true", "NULL;secondName;true", "NULL;NULL;true"}, nullValues = {"NULL"}, delimiter = ';')
    public void shouldCorrectlyHandleExceptionWhenUsingAllArgsConstructorAndFirstSecondNamesIsNullWhenPassedIsTrue(final String firstName,
                                                                                                                   final String secondName,
                                                                                                                   final boolean isStudentPassedTest) {
        assertThatThrownBy(() -> {
            try {
                // check the set method
                getWithSettingsQuestionTwoArgs(firstName, secondName, isStudentPassedTest);
            } catch (final MockitoException ex) {
                throw new IllegalArgumentException("The student first or second name cannot bu NULL");
            }
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("The student first or second name cannot bu NULL");
    }

    @ParameterizedTest
    @CsvSource(value = {"fistName;NULL;false", "NULL;secondName;false", "NULL;NULL;false"}, nullValues = {"NULL"}, delimiter = ';')
    public void shouldCorrectlyHandleExceptionWhenUsingAllArgsConstructorAndFirstSecondNamesIsNullWhenPassedIsFalse(final String firstName,
                                                                                                                    final String secondName,
                                                                                                                    final boolean isStudentPassedTest) {
        assertThatThrownBy(() -> {
            try {
                // check the set method
                getWithSettingsQuestionTwoArgs(firstName, secondName, isStudentPassedTest);
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
    @CsvSource(value = {"firstName;secondName;Student {firstName =firstName,secondName =secondName};true",
            "firstName;secondName;Student {firstName =firstName,secondName =secondName};false"}, delimiter = ';')
    public void shouldHaveCorrectlyToStringMethod(final String firstName,
                                                  final String secondName,
                                                  final String toStringCorrectResult,
                                                  final boolean isStudentPassedTests) {
        final Student student = getWithSettingsQuestionTwoArgs(firstName, secondName, isStudentPassedTests);

        given(student.getFirstName()).willReturn(firstName);
        given(student.getSecondName()).willReturn(secondName);
        given(student.isPassedTest()).willReturn(isStudentPassedTests);
        given(student.isBlank()).willReturn(false);
        given(student.toString()).willReturn(toStringCorrectResult);

        assertFalse(student.isBlank());
        assertEquals(firstName, student.getFirstName());
        assertEquals(secondName, student.getSecondName());
        assertEquals(isStudentPassedTests, student.isPassedTest());

        verify(student, times(1)).isBlank();
        verify(student, times(1)).getFirstName();
        verify(student, times(1)).getSecondName();
        verify(student, times(1)).isPassedTest();

        assertDoesNotThrow(() -> student.isBlank());
        assertDoesNotThrow(() -> student.getFirstName());
        assertDoesNotThrow(() -> student.getSecondName());
        assertDoesNotThrow(() -> student.isPassedTest());
    }

    private static Student getWithSettingsQuestionTwoArgs(String firstName, String secondName, boolean isStudentPassedTest) throws MockitoException {
        return mock(Student.class, getMocksWithSettings().useConstructor(firstName, secondName, isStudentPassedTest));
    }

    private static Student getWithSettingsQuestionNoArgs() throws MockitoException {
        return mock(Student.class, getMocksWithSettings().useConstructor());
    }

    private static MockSettings getMocksWithSettings() {
        return withSettings().defaultAnswer(RETURNS_MOCKS);
    }
}
