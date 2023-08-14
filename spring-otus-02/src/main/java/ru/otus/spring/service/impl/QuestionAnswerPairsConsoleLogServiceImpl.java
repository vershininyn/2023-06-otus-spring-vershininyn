package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.student.Student;
import ru.otus.spring.service.QuestionAnswerPairsLogService;

import java.util.Objects;
import java.util.StringJoiner;

@Service
public class QuestionAnswerPairsConsoleLogServiceImpl implements QuestionAnswerPairsLogService {
    @Override
    public void logAllInformation(Student student, boolean studentIsSuccessPassTests) {
        Objects.requireNonNull(student);

        String log = (new StringJoiner("", "", ""))
                .add("\r\n")
                .add(student.toString())
                .add("\r\n")
                .add("The tests is passed: ")
                .add(String.valueOf(studentIsSuccessPassTests))
                .toString();

        System.out.println(log);
    }
}
