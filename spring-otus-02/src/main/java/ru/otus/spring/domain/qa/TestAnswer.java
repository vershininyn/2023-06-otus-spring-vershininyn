package ru.otus.spring.domain.qa;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.StringJoiner;

@NoArgsConstructor
@Getter
public class TestAnswer {
    private String answer = "";

    public TestAnswer(String answer) {
        setAnswer(answer);
    }

    public TestAnswer setAnswer(String answer) {
        Objects.requireNonNull(answer, "Answer cannot be NULL");

        if (answer.isBlank()) {
            throw new IllegalArgumentException("Answer cannot be blank");
        }

        this.answer = answer;

        return this;
    }

    public boolean isBlank() {
        Objects.requireNonNull(answer, "Answer cannot be NULL");

        return answer.isBlank();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestAnswer that = (TestAnswer) o;
        return Objects.equals(answer, that.answer);
    }

    @Override
    public String toString() {
        return (new StringJoiner("","",""))
                .add("Answer: ")
                .add("{")
                .add("answer =")
                .add(answer)
                .add("}")
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(answer);
    }
}
