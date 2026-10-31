package exam.java.question.controller;

import exam.java.question.model.Question;
import exam.java.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam")
public class JavaQuestionController {

    private final QuestionService questionService;

    public JavaQuestionController(@Qualifier("javaQuestionService") QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/java")
    public List<Question> getAllJavaQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/java/add")
    public void addJavaQuestion(
            @RequestParam String question,
            @RequestParam String answer) {
        questionService.addQuestion(new Question(question, answer));
    }

    @GetMapping("/java/remove")
    public void removeJavaQuestion(
            @RequestParam String question,
            @RequestParam String answer) {
        questionService.removeQuestion(new Question(question, answer));
    }
}
