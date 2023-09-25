package ru.otus.spring.domain.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.otus.spring.domain.qa.TestAnswer;
import ru.otus.spring.domain.qa.TestQuestion;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StudentQuestionAnswerPair {
   private TestQuestion question;
   private TestAnswer answer;

   public StudentQuestionAnswerPair setQuestion(TestQuestion question) {
      Objects.requireNonNull(question, "Question cannot be NULL");

      if (question.isBlank()) {
         throw new IllegalArgumentException("Question of student cannot be empty");
      }

      this.question = question;

      return this;
   }

   public StudentQuestionAnswerPair setAnswer(TestAnswer answer) {
      Objects.requireNonNull(answer, "Answer cannot be NULL");

      this.answer = answer;

      return this;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      StudentQuestionAnswerPair that = (StudentQuestionAnswerPair) o;
      return Objects.equals(question, that.question) && Objects.equals(answer, that.answer);
   }

   @Override
   public int hashCode() {
      return Objects.hash(question, answer);
   }
}
