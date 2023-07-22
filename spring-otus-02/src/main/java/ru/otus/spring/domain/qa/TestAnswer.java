package ru.otus.spring.domain.qa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.StringJoiner;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TestAnswer {
    private String answer;

    public boolean isEmpty() {
        return answer.isEmpty();
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
