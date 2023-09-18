package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.domain.student.Student;
import ru.otus.spring.domain.testprocess.TestProcess;
import ru.otus.spring.service.QuestionAnswerPairsLogService;

@ComponentScan
public class Main {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Main.class);

        TestProcess process = ctx.getBean(TestProcess.class);

        Student student= process.runTestProcess();

        QuestionAnswerPairsLogService logService = ctx.getBean(QuestionAnswerPairsLogService.class);

        logService.logAllInformation(student);
    }
}
