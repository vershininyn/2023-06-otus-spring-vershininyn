package ru.otus.spring.domain.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.StringJoiner;

@AllArgsConstructor
@Getter
@Setter
public class Student {
    private String firstName;
    private String secondName;

    @Override
    public String toString() {
        return (new StringJoiner("","",""))
                .add("Student {")
                .add("firstName =")
                .add(firstName)
                .add(",")
                .add("secondName =")
                .add(secondName)
                .add("}")
                .toString();
    }
}
