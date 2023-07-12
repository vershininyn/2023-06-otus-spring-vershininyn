package ru.otus.spring.domain;

import lombok.*;

import java.util.StringJoiner;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Question {
    private QuestionType type;
    private String question;
    private String[] answers;

    @Override
    public String toString() {
        String answersJoined = "";

        if (answers != null && answers.length > 0) {
            answersJoined = String.join(",", answers);
        }

        return (new StringJoiner("", "", ""))
                .add("Question:")
                .add("{")
                .add("type:")
                .add(type.toString())
                .add(",question:")
                .add(question)
                .add(",answers:")
                .add("[")
                .add(answersJoined)
                .add("]")
                .add("}")
                .toString();
    }
}
