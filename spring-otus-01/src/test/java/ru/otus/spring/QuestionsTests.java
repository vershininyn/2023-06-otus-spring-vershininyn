package ru.otus.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;

import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.QuestionType;
import ru.otus.spring.service.IQuestionService;
import ru.otus.spring.service.QuestionServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class QuestionsTests {
    private final ObjectMapper mapper = new ObjectMapper();

    private static IQuestionService qService;

    @BeforeClass
    public static void setUp() throws Exception {
        qService = new QuestionServiceImpl();
    }

    @Test
    public void checkTestsCount() throws Exception {
        List<Question> qList = qService.getAllQuestions();

        assertEquals(5, qList.size());

        List<Question> freeQuestions = qList.stream()
                .filter(q -> q.getType().equals(QuestionType.free))
                .toList();

        assertEquals(2, freeQuestions.size());

        List<Question> optionalQuestions = qList.stream()
                .filter(q -> q.getType().equals(QuestionType.optional))
                .toList();

        assertEquals(3, optionalQuestions.size());
    }


    @Test
    public void checkOptionalQuestions() throws Exception {
        List<Question> qList = qService.getAllOptionalQuestions();

        List<String> optionalQuestions = qList.stream()
                .filter(q -> q.getType().equals(QuestionType.optional))
                .map(Question::getQuestion)
                .toList();

        assertTrue(optionalQuestions.contains("Are you male or female?"));
        assertTrue(optionalQuestions.contains("What is your favorite color?"));
        assertTrue(optionalQuestions.contains("What is your favorite animal?"));
    }

    @Test
    public void checkFreeQuestions() throws Exception {
        List<Question> qList = qService.getAllFreeQuestions();

        List<String> optionalQuestions = qList.stream()
                .filter(q -> q.getType().equals(QuestionType.free))
                .map(Question::getQuestion)
                .toList();

        assertTrue(optionalQuestions.contains("How old are you?"));
        assertTrue(optionalQuestions.contains("What is your weight?"));
    }

}
