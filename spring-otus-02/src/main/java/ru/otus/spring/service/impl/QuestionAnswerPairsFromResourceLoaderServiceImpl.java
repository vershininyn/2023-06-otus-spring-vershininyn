package ru.otus.spring.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.service.QuestionAnswerPairsFromResourceLoaderService;

import java.io.InputStream;

@Service
@AllArgsConstructor
public class QuestionAnswerPairsFromResourceLoaderServiceImpl implements QuestionAnswerPairsFromResourceLoaderService {
    private final String jsonFilename;

    @Override
    public InputStream loadJsonInputStreamFromResource() {
        return QuestionAnswerPairsExtractServiceImpl.class.getClassLoader().getResourceAsStream(jsonFilename);
    }
}
