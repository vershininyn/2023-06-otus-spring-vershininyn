package ru.otus.spring.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.QuestionType;
import ru.otus.spring.service.QuestionsExtractService;
import ru.otus.spring.util.QuestionsFromResourcesLoaderUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Stream;

import static ru.otus.spring.domain.QuestionType.free;
import static ru.otus.spring.domain.QuestionType.optional;

public class QuestionsExtractServiceImpl implements QuestionsExtractService {
    private final ObjectMapper mapper = new ObjectMapper();

    private QuestionsFromResourcesLoaderUtil qLoader;

    public List<Question> qList;

    public QuestionsExtractServiceImpl(QuestionsFromResourcesLoaderUtil qLoader) throws Exception {
        this.qLoader = qLoader;
    }

    @Override
    public List<Question> getAllQuestions() throws NullPointerException {
        return Stream.concat(getAllFreeQuestions().stream(), getAllOptionalQuestions().stream()).toList();
    }

    @Override
    public List<Question> getAllOptionalQuestions() throws NullPointerException {
        return getAllQuestionsByType(optional);
    }

    @Override
    public List<Question> getAllFreeQuestions() throws NullPointerException {
        return getAllQuestionsByType(free);
    }

    private List<Question> getAllQuestionsByType(QuestionType type) {
        if (qList == null) {
            lazyLoadAllQuestions();
        }

        return qList.stream()
                .filter(q -> q.getType().equals(type))
                .toList();
    }

    private void lazyLoadAllQuestions() throws NullPointerException {
        InputStream jsonIOStream = qLoader.loadQuestionsFromResources();

        try {
            qList = mapper.readValue(jsonIOStream, new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
