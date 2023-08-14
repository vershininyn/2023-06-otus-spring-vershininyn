package ru.otus.spring.integrations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.config.QuestionAnswerPairsFromResourceLoaderServiceConfiguration;
import ru.otus.spring.config.QuestionAnswerPairsVerifyServiceConfiguration;
import ru.otus.spring.service.QuestionAnswerPairsExtractService;
import ru.otus.spring.service.QuestionAnswerPairsFromResourceLoaderService;
import ru.otus.spring.service.QuestionAnswerPairsVerifyService;

@ExtendWith(SpringExtension.class)
@ActiveProfiles(profiles = "test")
@ContextConfiguration(classes = {QuestionAnswerPairsFromResourceLoaderServiceConfiguration.class,
        QuestionAnswerPairsVerifyServiceConfiguration.class})
public class TestProcessCliTests {
    private final ObjectMapper questionMapper = new ObjectMapper();

    private QuestionAnswerPairsFromResourceLoaderService loaderServiceImpl;

    private QuestionAnswerPairsExtractService extractServiceImpl;

    private QuestionAnswerPairsVerifyService verifyServiceImpl;

    public TestProcessCliTests(QuestionAnswerPairsFromResourceLoaderService loaderService,
                               QuestionAnswerPairsExtractService extractService,
                               QuestionAnswerPairsVerifyService verifyService) {
        this.loaderServiceImpl = loaderService;
        this.extractServiceImpl = extractService;
        this.verifyServiceImpl = verifyService;
    }

    @Test
    public void inputStreamLoaderServiceIsCorrect() {

    }

    @Test
    public void extractServiceGetAllOptionalQuestionsAnswerPairsIsCorrectTest() {

    }

    @Test
    public void verifyServiceThatStudentIsPassTheTest() {

    }

    @Test
    public void verifyServiceThatStudentIsNotPassTheTest() {

    }
}
