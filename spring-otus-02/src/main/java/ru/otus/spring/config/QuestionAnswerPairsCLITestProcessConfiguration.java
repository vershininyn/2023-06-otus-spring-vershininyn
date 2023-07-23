package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.domain.testprocess.impl.CLITestProcessImpl;
import ru.otus.spring.domain.testprocess.TestProcess;

@Configuration
public class QuestionAnswerPairsCLITestProcessConfiguration {
    @Bean("CLITestProcess")
    public TestProcess configureTestProcess() {
        return new CLITestProcessImpl();
    }
}
