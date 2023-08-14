package ru.otus.spring.integrations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.Main;
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
    private final ObjectMapper qMapper = new ObjectMapper();

    private static QuestionAnswerPairsFromResourceLoaderService loaderServiceImpl;

    private static QuestionAnswerPairsExtractService extractServiceImpl;

    private static QuestionAnswerPairsVerifyService verifyServiceImpl;

    @BeforeAll
    public static void beforeAll() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        loaderServiceImpl = context.getBean(QuestionAnswerPairsFromResourceLoaderService.class);
        extractServiceImpl = context.getBean(QuestionAnswerPairsExtractService.class);
        verifyServiceImpl = context.getBean(QuestionAnswerPairsVerifyService.class);
    }

    @Test
    public void someIntegrationTest() {

    }
}
