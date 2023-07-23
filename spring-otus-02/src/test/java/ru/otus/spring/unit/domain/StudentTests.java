package ru.otus.spring.unit.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.otus.spring.domain.qa.QuestionType;
import ru.otus.spring.domain.qa.TestQuestion;
import ru.otus.spring.domain.student.Student;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTests {
    @ParameterizedTest
    @CsvSource(value = {"Ivan:Ivan"}, delimiter = ':')
    public void studentObjShouldHaveCorrectConstructor(String studentFirstName, String studentSecondName) {
        Student student = new Student(studentFirstName, studentSecondName);

        assertEquals(studentFirstName, student.getFirstName());
        assertEquals(studentSecondName, student.getSecondName());
        assertFalse(student.isBlank());
    }

    @Test
    public void studentObjShouldBeEmptyWithoutNamesInitialization() {
        Student student = new Student();

        assertTrue(student.getFirstName().isBlank());
        assertTrue(student.getSecondName().isBlank());
        assertFalse(student.isBlank());
    }


    @ParameterizedTest
    @CsvSource(value = {"Ivan:Ivan"}, delimiter = ':')
    public void studentObjShouldHaveCorrectFirstAndSecondNamesSetter(String studentFirstName, String studentSecondName) {
        Student student = new Student();

        student.setFirstName(studentFirstName);
        student.setSecondName(studentSecondName);

        assertFalse(student.isBlank());
        assertEquals(studentFirstName, student.getFirstName());
        assertEquals(studentSecondName, student.getSecondName());
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
