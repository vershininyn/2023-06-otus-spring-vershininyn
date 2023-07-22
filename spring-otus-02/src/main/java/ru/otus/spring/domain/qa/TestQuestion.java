package ru.otus.spring.domain.qa;

import lombok.*;

import java.util.Objects;
import java.util.StringJoiner;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TestQuestion {
    private QuestionType type;
    private String questionString;

    @Override
    public String toString() {
        return (new StringJoiner("", "", ""))
                .add("Question:")
                .add("{")
                .add("type:")
                .add(type.toString())
                .add(",question:")
                .add(questionString)
                .add("}")
                .toString();
    }

    public boolean isEmpty() {
        return questionString.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestQuestion that = (TestQuestion) o;
        return type == that.type && Objects.equals(questionString, that.questionString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, questionString);
    }
}
