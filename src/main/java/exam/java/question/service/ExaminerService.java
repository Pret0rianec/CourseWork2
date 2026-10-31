package exam.java.question.service;

import exam.java.question.model.Question;

import java.util.List;

public interface ExaminerService {
    List<Question> getQuestions(int amount);
}
