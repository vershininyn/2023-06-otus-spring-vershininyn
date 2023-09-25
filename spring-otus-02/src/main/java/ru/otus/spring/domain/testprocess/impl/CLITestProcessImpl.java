package ru.otus.spring.domain.testprocess.impl;

import org.springframework.stereotype.Component;
import ru.otus.spring.domain.qa.TestAnswer;
import ru.otus.spring.domain.qa.TestQuestion;
import ru.otus.spring.domain.student.Student;
import ru.otus.spring.domain.student.StudentQuestionAnswerPair;
import ru.otus.spring.domain.testprocess.TestProcess;
import ru.otus.spring.service.QuestionAnswerPairsExtractService;
import ru.otus.spring.service.QuestionAnswerPairsVerifyService;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import static ru.otus.spring.domain.qa.QuestionType.optional;

@Component
public class CLITestProcessImpl implements TestProcess {

    private QuestionAnswerPairsExtractService qaExtractService;


    private QuestionAnswerPairsVerifyService verifyService;




    public CLITestProcessImpl(QuestionAnswerPairsExtractService qaExtractService,
                              QuestionAnswerPairsVerifyService verifyService) {
        this.qaExtractService = qaExtractService;
        this.verifyService = verifyService;
    }

    public Student runTestProcess() {
        try (final InputStream inStream = System.in;
             final Scanner scanner = new Scanner(inStream)) {
            Student student = getStudentFromCLI(scanner, false);

            final Map<Long, StudentQuestionAnswerPair> trueQuestionAnswerPairs = qaExtractService.getAllOptionalQuestionAnswerPairs(),
                    actualQuestionAnswerPairs = getActualQuestionAnswerPairs(trueQuestionAnswerPairs, scanner);

            boolean studentIsPassedTests
                    = verifyService.isStudentSuccessfullyPassTheTest(actualQuestionAnswerPairs, trueQuestionAnswerPairs);

            student.setPassedTest(studentIsPassedTests);

            return student;
        }
        catch(Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    private Map<Long, StudentQuestionAnswerPair>
    getActualQuestionAnswerPairs(final Map<Long, StudentQuestionAnswerPair> trueQuestionAnswerPairs,
                                 final Scanner scanner) {
        Objects.requireNonNull(scanner, "Scanner cannot be NULL");

        final Map<Long, StudentQuestionAnswerPair> actualQuestionAnswerPairs = new HashMap<Long, StudentQuestionAnswerPair>(10);

        trueQuestionAnswerPairs.forEach((k, v) -> {
            String questionStr = v.getQuestion().getQuestionString();

            System.out.println("[" + k + "] Question: " + questionStr);
            System.out.print("Please, enter the answer for the above question: ");

            actualQuestionAnswerPairs.put(k,
                    new StudentQuestionAnswerPair(new TestQuestion(optional, questionStr), new TestAnswer(scanner.nextLine())));
        });

        return actualQuestionAnswerPairs;
    }

    private Student getStudentFromCLI(final Scanner scanner, boolean isStudentPassTheTests) {
        Objects.requireNonNull(scanner, "Scanner cannot be NULL");

        String firstName = null, secondName = null;

        System.out.print("Please, enter your first name: ");

        if (!scanner.hasNext("[a-zA-Z]{2,}")) {
            throw new IllegalArgumentException("Please, check your first name.");
        }

        firstName = scanner.nextLine();

        System.out.print("Please, enter your second name: ");

        if (!scanner.hasNext("[a-zA-Z]{2,}")) {
            throw new IllegalArgumentException("Please, check your second name.");
        }

        secondName = scanner.nextLine();

        return new Student(firstName, secondName, isStudentPassTheTests);
    }
}
