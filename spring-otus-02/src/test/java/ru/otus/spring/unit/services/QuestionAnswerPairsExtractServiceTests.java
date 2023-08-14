package ru.otus.spring.unit.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.config.QuestionAnswerPairsFromResourceLoaderServiceConfiguration;
import ru.otus.spring.config.QuestionAnswerPairsVerifyServiceConfiguration;
import ru.otus.spring.domain.student.StudentQuestionAnswerPair;
import ru.otus.spring.service.QuestionAnswerPairsExtractService;

import java.io.InputStream;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ActiveProfiles(profiles = "test")
@ContextConfiguration(classes = {QuestionAnswerPairsFromResourceLoaderServiceConfiguration.class,
        QuestionAnswerPairsVerifyServiceConfiguration.class})
@ComponentScan("ru.otus.spring")
public class QuestionAnswerPairsExtractServiceTests {
    private ObjectMapper qMapper = new ObjectMapper();

    private QuestionAnswerPairsExtractService extractServiceImpl;

    public QuestionAnswerPairsExtractServiceTests(QuestionAnswerPairsExtractService extractService) {
        this.extractServiceImpl = extractService;
    }

    @Test
    public void shouldBeCorrectQuestionAnswerPairsExtractResult() {
        try (InputStream qInStream
                     = QuestionAnswerPairsExtractServiceTests.class.getClassLoader().getResourceAsStream("questions-test.json")) {

            Map<Long, StudentQuestionAnswerPair> trueQuestionAnswerPairsMap = qMapper.readValue(qInStream, new TypeReference<>() {}),
                    actualQuestionAnswerPairsMap = extractServiceImpl.getAllQuestionAnswerPairs();

            /**
             * Эквивалентность карт пар вопрос-ответ:
             *
             * 0. Количество в первой = Количество во второй
             * 1. Множества ключей совершенно идентичны? (либо |B - A| > 0, либо |A - B| > 0)
             * 2. Множества значений совершенно идентичны? (либо |B - A| > 0, либо |A - B| > 0)
             */

            assertEquals(trueQuestionAnswerPairsMap.size(), actualQuestionAnswerPairsMap.size());

            Set<Long> trueKeySet = trueQuestionAnswerPairsMap.keySet(), // A
                    actualKeySet = actualQuestionAnswerPairsMap.keySet(); // B

            // |A - B| > 0 ???
            long diffTrueKeysMinusActualKeysSetSize = trueKeySet.stream()
                    .filter(l -> !actualKeySet.contains(l))
                    .count();

            assertEquals(0, diffTrueKeysMinusActualKeysSetSize);

            // |B - A| > 0 ???
            long diffActualKeysMinusTrueKeysSetSize = actualKeySet.stream()
                    .filter(l -> !trueKeySet.contains(l))
                    .count();

            assertEquals(0, diffActualKeysMinusTrueKeysSetSize);

            Collection<StudentQuestionAnswerPair> trueStudentQuestionAnswerPairsCollection = trueQuestionAnswerPairsMap.values(), // A
                    actualStudentQuestionAnswerPairsCollection = actualQuestionAnswerPairsMap.values(); // B

            // |A - B| > 0 ???
            long diffTrueValuesMinusActualValuesCollectionSize =  trueStudentQuestionAnswerPairsCollection.stream()
                    .filter(sqap -> !actualStudentQuestionAnswerPairsCollection.contains(sqap))
                    .count();

            assertEquals(0, diffTrueValuesMinusActualValuesCollectionSize);

            // |B - A| > 0 ???
            long diffActualValuesMinusTrueValuesCollectionSize = actualStudentQuestionAnswerPairsCollection.stream()
                    .filter(sqap -> !trueStudentQuestionAnswerPairsCollection.contains(sqap))
                    .count();

            assertEquals(0, diffActualValuesMinusTrueValuesCollectionSize);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
