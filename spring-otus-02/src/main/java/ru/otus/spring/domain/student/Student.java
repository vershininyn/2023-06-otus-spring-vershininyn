package ru.otus.spring.domain.student;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.StringJoiner;

@NoArgsConstructor
@Getter
public class Student {
    private String firstName = "";
    private String secondName = "";

    public Student(String firstName, String secondName) {
        setFirstName(firstName);
        setSecondName(secondName);
    }

    public Student setFirstName(String firstName) throws IllegalArgumentException{
        checkStudentSomeName(firstName, "first name");

        this.firstName = firstName;

        return this;
    }

    public Student setSecondName(String secondName) throws IllegalArgumentException{
        checkStudentSomeName(secondName, "second name");

        this.secondName = secondName;

        return this;
    }

    public boolean isBlank() {
        return (getFirstName().isBlank()) && (getSecondName().isBlank());
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(firstName, student.firstName) && Objects.equals(secondName, student.secondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName);
    }

    private void checkStudentSomeName(String name, String nameType) throws IllegalArgumentException {
        Objects.requireNonNull(name, String.format("Student %s cannot be NULL", nameType));

        if (name.isBlank()) {
            throw new IllegalArgumentException(String.format("Student %s cannot be blank", nameType));
        }
    }
}
