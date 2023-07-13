package ru.otus.spring.util;

import ru.otus.spring.service.impl.QuestionsExtractServiceImpl;

import java.io.InputStream;

public class QuestionsFromResourcesLoaderUtil {
    private String jsonFilename;

    public QuestionsFromResourcesLoaderUtil(String questionJsonFilename) {
        jsonFilename = questionJsonFilename;
    }

    public String getJsonFilename() {
        return jsonFilename;
    }

    public InputStream loadQuestionsFromResources() {
        return QuestionsExtractServiceImpl.class.getClassLoader().getResourceAsStream(jsonFilename);
    }
}
