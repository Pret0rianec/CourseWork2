package exam.java.question.controller;

import exam.java.question.model.Question;
import exam.java.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/exam")
public class MathQuestionController {
    private final QuestionService questionService;

    public MathQuestionController(@Qualifier("mathQuestionService") QuestionService questionService) {this.questionService = questionService;}

    @GetMapping("/math")
    public List<Question> getAllMathQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/math/add")
    public void addMathQuestion(@RequestParam String question, @RequestParam String answer) {
        questionService.addQuestion(new Question(question, answer));
    }

    @GetMapping("/math/remove")
    public void removeMathQuestion(@RequestParam String question, @RequestParam String answer) {
        questionService.removeQuestion(new Question(question, answer));
    }
}
