package ru.otus.spring;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.spring.domain.qa.TestQuestion;
import ru.otus.spring.domain.student.StudentQuestionAnswerPair;
import ru.otus.spring.service.QuestionAnswerPairsExtractService;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static ru.otus.spring.domain.qa.QuestionType.free;
import static ru.otus.spring.domain.qa.QuestionType.optional;

@ActiveProfiles(value = "test")
public class QuestionAnswerPairsExtractServiceTests {
    @Autowired
    private QuestionAnswerPairsExtractService qService;

    @Test
    public void checkTestsCount() throws Exception {
        Map<Long, StudentQuestionAnswerPair> qMap = qService.getAllQuestionAnswerPairs();

        assertEquals(5, qMap.size());

        List<TestQuestion> freeTestQuestions = qMap.values()
                .stream()
                .map(StudentQuestionAnswerPair::getQuestion)
                .filter(q -> q.getType().equals(free))
                .toList();

        assertEquals(2, freeTestQuestions.size());

        List<TestQuestion> optionalTestQuestions = qMap.values()
                .stream()
                .map(StudentQuestionAnswerPair::getQuestion)
                .filter(q -> q.getType().equals(optional))
                .toList();

        assertEquals(3, optionalTestQuestions.size());
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
        List<StudentQuestionAnswerPair> optTestQuestions = qService.getAllQuestionAnswerPairs()
                .values()
                .stream()
                .toList();

        return convertQuestionsToStrings(optTestQuestions);
    }

    private List<String> getFreeQuestionsValue() {
        List<StudentQuestionAnswerPair> freeTestQuestions = qService.getAllFreeQuestionAnswerPairs()
                .values()
                .stream()
                .toList();

        return convertQuestionsToStrings(freeTestQuestions);
    }

    private List<String> convertQuestionsToStrings(List<StudentQuestionAnswerPair> list) {
        return list.stream()
                .map(StudentQuestionAnswerPair::getQuestion)
                .map(TestQuestion::toString)
                .toList();
    }
}
