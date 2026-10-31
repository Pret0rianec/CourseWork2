package exam.java.question.service;

import exam.java.question.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService javaQuestionService;
    private final QuestionService mathQuestionService;

    @Autowired
    public ExaminerServiceImpl(
            @Qualifier("javaQuestionService") QuestionService javaQuestionService,
            @Qualifier("mathQuestionService") QuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

    @Override
    public List<Question> getQuestions(int amount) {
        if (amount <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Цифра должна быть положительной");
        }
        List<Question> allJava = javaQuestionService.getAllQuestions();
        List<Question> allMath = mathQuestionService.getAllQuestions();
        List<Question> combined = new ArrayList<>(allJava);
        combined.addAll(allMath);
        if (amount > combined.size()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Запрошенное количество (" + amount + ") превышает число доступных вопросов (" + combined.size() + ")"
            );
        }
        Collections.shuffle(combined);

        return combined.subList(0, amount);
    }
}
