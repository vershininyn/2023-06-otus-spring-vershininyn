package ru.otus.spring.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.qa.QuestionType;
import ru.otus.spring.domain.student.StudentQuestionAnswerPair;
import ru.otus.spring.service.QuestionAnswerPairsExtractService;
import ru.otus.spring.service.QuestionAnswerPairsFromResourceLoaderService;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.otus.spring.domain.qa.QuestionType.free;
import static ru.otus.spring.domain.qa.QuestionType.optional;

@Service
public class QuestionAnswerPairsExtractServiceImpl implements QuestionAnswerPairsExtractService {
    private final ObjectMapper mapper = new ObjectMapper();

    private final QuestionAnswerPairsFromResourceLoaderService loadFromResourcesService;

    public Map<Long, StudentQuestionAnswerPair> qMap;

    public QuestionAnswerPairsExtractServiceImpl(QuestionAnswerPairsFromResourceLoaderService loadFromResourcesService) {
        this.loadFromResourcesService = loadFromResourcesService;
    }

    @Override
    public Map<Long, StudentQuestionAnswerPair> getAllQuestionAnswerPairs() {
        return Stream.of(getAllFreeQuestionAnswerPairs(), getAllOptionalQuestionAnswerPairs())
                .map(Map::entrySet)
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public Map<Long, StudentQuestionAnswerPair> getAllOptionalQuestionAnswerPairs() {
        return getAllQuestionsByType(optional);
    }

    @Override
    public Map<Long, StudentQuestionAnswerPair> getAllFreeQuestionAnswerPairs() {
        return getAllQuestionsByType(free);
    }

    private Map<Long, StudentQuestionAnswerPair> getAllQuestionsByType(QuestionType type) {
        Objects.requireNonNull(type);

        if (qMap == null) {
            qMap = lazyLoadAllQuestionAnswerPairsMap();
        }

        return qMap.entrySet()
                .stream()
                .filter(e -> e.getValue().getQuestion().getType().equals(type))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Map<Long, StudentQuestionAnswerPair> lazyLoadAllQuestionAnswerPairsMap() {
        InputStream jsonIOStream = loadFromResourcesService.loadJsonInputStreamFromResource();

        Map<Long, StudentQuestionAnswerPair> map = null;

        try {
            map = mapper.readValue(jsonIOStream, new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return map;
    }
}
