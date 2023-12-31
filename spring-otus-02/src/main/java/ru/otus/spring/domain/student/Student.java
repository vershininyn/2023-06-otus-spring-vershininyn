package ru.otus.spring.domain.student;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.StringJoiner;

@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class Student {
    private String firstName = "";
    private String secondName = "";
    @Setter
    private boolean isPassedTest = false;

    public Student(String firstName,
                   String secondName,
                   boolean isPassedTest) {
        setFirstName(firstName);
        setSecondName(secondName);
        setPassedTest(isPassedTest);
    }

    public Student setFirstName(String firstName) {
        checkStudentSomeName(firstName, "first name");

        this.firstName = firstName;

        return this;
    }

    public Student setSecondName(String secondName) {
        checkStudentSomeName(secondName, "second name");

        this.secondName = secondName;

        return this;
    }

    public boolean isBlank() {
        Objects.requireNonNull(getFirstName(), "The first name cannot be NULL");
        Objects.requireNonNull(getSecondName(), "The second name cannot be NULL");

        return (getFirstName().isBlank()) && (getSecondName().isBlank());
    }

    @Override
    public String toString() {
        return (new StringJoiner("", "", ""))
                .add("Student {")
                .add("firstName =")
                .add(firstName)
                .add(",")
                .add("secondName =")
                .add(secondName)
                .add("}")
                .toString();
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Student student = (Student) o;
//        return Objects.equals(firstName, student.firstName) && Objects.equals(secondName, student.secondName);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(firstName, secondName);
//    }

    private void checkStudentSomeName(String name, String nameType) {
        Objects.requireNonNull(name, String.format("Student %s cannot be NULL", nameType));

        if (name.isBlank()) {
            throw new IllegalArgumentException(String.format("Student %s cannot be blank", nameType));
        }
    }
}
