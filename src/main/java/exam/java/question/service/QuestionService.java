package exam.java.question.service;

import exam.java.question.model.Question;

import java.util.List;

public interface QuestionService {
    void addQuestion(Question question);

    void removeQuestion(Question question);

    List<Question> getAllQuestions();
}

