package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.domain.testprocess.CLITestProcess;
import ru.otus.spring.domain.testprocess.TestProcess;

@Configuration
public class QuestionAnswerPairsCLITestProcessConfiguration {
    @Bean("CLITestProcess")
    public TestProcess configureTestProcess() {
        return new CLITestProcess();
    }
}
