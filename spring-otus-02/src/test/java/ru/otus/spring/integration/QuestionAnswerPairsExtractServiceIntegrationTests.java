package ru.otus.spring.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.spring.domain.qa.TestQuestion;
import ru.otus.spring.domain.student.StudentQuestionAnswerPair;
import ru.otus.spring.service.QuestionAnswerPairsExtractService;
import ru.otus.spring.service.QuestionAnswerPairsFromResourceLoaderService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static ru.otus.spring.domain.qa.QuestionType.free;
import static ru.otus.spring.domain.qa.QuestionType.optional;

@ActiveProfiles(profiles = "test", value = "test")
public class QuestionAnswerPairsExtractServiceIntegrationTests {
    @Value("${tests.students.csv_resource_filename}")
    private String questionJsonFilename;

    @Mock
    private QuestionAnswerPairsFromResourceLoaderService qLoaderService;

    @InjectMocks
    private QuestionAnswerPairsExtractService qExtractService;

    @BeforeEach
    public void beforeEach() throws IOException {
        try (InputStream jsonInputStream =
                     QuestionAnswerPairsExtractServiceIntegrationTests.class.getResourceAsStream(questionJsonFilename))
        {
            when(qLoaderService.loadJsonInputStreamFromResource()).thenReturn(jsonInputStream);
        }
    }

    @Test
    public void checkTestsCount() throws Exception {
        Map<Long, StudentQuestionAnswerPair> qMap = qExtractService.getAllQuestionAnswerPairs();

        assertEquals(5, qMap.size());

        List<TestQuestion> freeTestQuestions = qMap.values()
                .stream()
                .map(StudentQuestionAnswerPair::getQuestion)
                .filter(q -> q.getType().equals(free))
                .toList();

        assertEquals(4, freeTestQuestions.size());

        List<TestQuestion> optionalTestQuestions = qMap.values()
                .stream()
                .map(StudentQuestionAnswerPair::getQuestion)
                .filter(q -> q.getType().equals(optional))
                .toList();

        assertEquals(1, optionalTestQuestions.size());
    }

    @Test
    public void checkOptionalQuestions_withExistsData() throws Exception {
        List<String> optionalQuestions = getOptionalQuestionsValue();

        assertTrue(optionalQuestions.contains("How much is 1.0 + 1.0?"));
        assertTrue(optionalQuestions.contains("How much is 3.0 - 5.0?"));
        assertTrue(optionalQuestions.contains("How much is 100.0*2.0?"));
        assertTrue(optionalQuestions.contains("How much is 100.0/2.0?"));
    }

    @Test
    public void checkOptionalQuestions_withNotExistsData() throws Exception {
        List<String> optionalQuestions = getOptionalQuestionsValue();

        assertFalse(optionalQuestions.contains("Something not exists"));
    }

    @Test
    public void checkFreeQuestions_withExistsData() throws Exception {
        List<String> freeQuestions = getFreeQuestionsValue();

        assertTrue(freeQuestions.contains("It's free question"));
    }

    @Test
    public void checkFreeQuestions_withNotExistsData() throws Exception {
        List<String> freeQuestions = getFreeQuestionsValue();

        assertFalse(freeQuestions.contains("Something not exists"));
    }

    private List<String> getOptionalQuestionsValue() {
        List<StudentQuestionAnswerPair> optTestQuestions = qExtractService.getAllOptionalQuestionAnswerPairs()
                .values()
                .stream()
                .toList();

        return convertQuestionsToStrings(optTestQuestions);
    }

    private List<String> getFreeQuestionsValue() {
        List<StudentQuestionAnswerPair> freeTestQuestions = qExtractService.getAllFreeQuestionAnswerPairs()
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
