package ru.otus.spring.util;

import ru.otus.spring.service.impl.QuestionLoaderServiceImpl;

import java.io.InputStream;

public class QuestionFromResourcesLoaderUtil {
    private String jsonFilename;

    public QuestionFromResourcesLoaderUtil(String questionJsonFilename) {
        jsonFilename = questionJsonFilename;
    }

    public String getJsonFilename() {
        return jsonFilename;
    }

    public InputStream loadQuestionsFromResources() {
        return QuestionLoaderServiceImpl.class.getClassLoader().getResourceAsStream(jsonFilename);
    }
}
