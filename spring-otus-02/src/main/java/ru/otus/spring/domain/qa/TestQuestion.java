package ru.otus.spring.domain.qa;

import lombok.*;

import java.util.Objects;
import java.util.StringJoiner;

@NoArgsConstructor
@Getter
public class TestQuestion {
    private QuestionType type;
    private String questionString = "";

    public TestQuestion(QuestionType type, String questionString) {
        setQuestionType(type);
        setQuestionString(questionString);
    }

    public TestQuestion setQuestionType(QuestionType type) {
        Objects.requireNonNull(type, "Question type cannot be NULL");

        this.type = type;

        return this;
    }

    public TestQuestion setQuestionString(String questionString) {
        Objects.requireNonNull(questionString, "Question string cannot be NULL");

        if (questionString.isBlank()) {
            throw new IllegalArgumentException("Question string cannot be blank");
        }

        this.questionString = questionString;

        return this;
    }

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

    public boolean isBlank() {
        Objects.requireNonNull(questionString, "Question string cannot be NULL");

        return questionString.isBlank();
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
