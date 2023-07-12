package ru.otus.spring.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.QuestionType;

import java.io.InputStream;
import java.util.List;

public class QuestionServiceImpl implements IQuestionService {
    private final ObjectMapper mapper = new ObjectMapper();

    public List<Question> qList;

    public QuestionServiceImpl() throws Exception {
        InputStream jsonIOStream = QuestionServiceImpl.class.getClassLoader().getResourceAsStream("questions.json");

        qList = mapper.readValue(jsonIOStream, new TypeReference<>() {});
    }

    @Override
    public List<Question> getAllQuestions() {
        return qList;
    }

    @Override
    public List<Question> getAllOptionalQuestions() {
        return qList.stream()
                .filter(q -> q.getType().equals(QuestionType.optional))
                .toList();
    }

    @Override
    public List<Question> getAllFreeQuestions() {
        return qList.stream()
                .filter(q -> q.getType().equals(QuestionType.free))
                .toList();
    }
}
