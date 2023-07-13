package ru.otus.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.QuestionType;
import ru.otus.spring.service.QuestionLoaderService;
import ru.otus.spring.service.impl.QuestionLoaderServiceImpl;
import ru.otus.spring.util.QuestionFromResourcesLoaderUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionsTests {
    private final ObjectMapper mapper = new ObjectMapper();

    private static QuestionLoaderService qService;

    @BeforeClass
    public static void setUp() throws Exception {
        qService = new QuestionLoaderServiceImpl(new QuestionFromResourcesLoaderUtil("questions.json"));
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
    public void checkOptionalQuestions_withExistsData() throws Exception {
        List<String> optionalQuestions = getOptionalQuestionsValue();

        assertTrue(optionalQuestions.contains("Are you male or female?"));
        assertTrue(optionalQuestions.contains("What is your favorite color?"));
        assertTrue(optionalQuestions.contains("What is your favorite animal?"));
    }

    @Test
    public void checkOptionalQuestions_withNotExistsData() throws Exception {
        List<String> optionalQuestions = getOptionalQuestionsValue();

        assertFalse(optionalQuestions.contains("Something not exists"));
    }

    @Test
    public void checkFreeQuestions_withExistsData() throws Exception {
        List<String> freeQuestions = getFreeQuestionsValue();

        assertTrue(freeQuestions.contains("How old are you?"));
        assertTrue(freeQuestions.contains("What is your weight?"));
    }

    @Test
    public void checkFreeQuestions_withNotExistsData() throws Exception {
        List<String> freeQuestions = getFreeQuestionsValue();

        assertFalse(freeQuestions.contains("Something not exists"));
    }

    private List<String> getOptionalQuestionsValue() {
        List<Question> optQuestions = qService.getAllOptionalQuestions();

        return convertQuestionsToStrings(optQuestions);
    }

    private List<String> getFreeQuestionsValue() {
        List<Question> freeQuestions = qService.getAllFreeQuestions();

        return convertQuestionsToStrings(freeQuestions);
    }

    private List<String> convertQuestionsToStrings(List<Question> list) {
        return list.stream()
                .map(Question::getQuestion)
                .toList();
    }
}
