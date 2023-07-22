package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.domain.testprocess.TestProcess;

@ComponentScan
public class Main {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Main.class);

        TestProcess process = ctx.getBean(TestProcess.class);

        process.runTestProcessAndLogResult();
    }
}
