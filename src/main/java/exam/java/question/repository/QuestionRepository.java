package exam.java.question.repository;

import exam.java.question.model.Question;

import java.util.List;

public interface QuestionRepository {
    void add(Question question);

    void remove(Question question);

    List<Question> getAll();
}
