package exam.java.question.test;

import exam.java.question.model.Question;
import exam.java.question.service.ExaminerServiceImpl;
import exam.java.question.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExaminerServiceImplTest {

    @Mock
    private QuestionService javaQuestionService;
    @Mock
    private QuestionService mathQuestionService;

    private ExaminerServiceImpl examinerService;

    @Test
    void setup() {
        MockitoAnnotations.openMocks(this);
        examinerService = new ExaminerServiceImpl(javaQuestionService, mathQuestionService);
    }

    @Test
    void getQuestions_MixSources_Success() {
        List<Question> javaQues = Arrays.asList(
                new Question("Java Q1", "A1"),
                new Question("Java Q2", "A2"),
                new Question("Java Q3", "A3")
        );
        List<Question> mathQues = Arrays.asList(
                new Question("Math Q1", "B1"),
                new Question("Math Q2", "B2"),
                new Question("Math Q3", "B3")
        );
        when(javaQuestionService.getAllQuestions()).thenReturn(javaQues);
        when(mathQuestionService.getAllQuestions()).thenReturn(mathQues);

        List<Question> result = examinerService.getQuestions(3);
        assertEquals(3, result.size());
        assertTrue(result.stream().distinct().count() == result.size());
        boolean hasJava = result.stream()
                .anyMatch(q->q.getQuestion().startsWith("Java"));
        boolean hasMath = result.stream()
                .allMatch(q->q.getQuestion().startsWith("Math"));
        assertTrue(hasJava&&hasMath, "Результат должен содержать вопросы из обоих предметов");
    }

    @Test
    void getQuestions_AmountExceedsTotal_ThrowsBadRequest() {
        List<Question> javaQues = List.of(new Question("Java Q1", "A1"));
        List<Question> mathQues = List.of(new Question("Math Q1", "B1"));
        when(javaQuestionService.getAllQuestions()).thenReturn(javaQues);
        when(mathQuestionService.getAllQuestions()).thenReturn(mathQues);

        ResponseStatusException ex = assertThrows(
                ResponseStatusException.class,
                () -> examinerService.getQuestions(5)
        );
        assertEquals(HttpStatus.BAD_REQUEST.value(), ex.getStatusCode().value());
    }

    @Test
    void getQuestions_ZeroOrNegative_ThrowsBadRequest() {
        when(javaQuestionService.getAllQuestions()).thenReturn(List.of());
        when(mathQuestionService.getAllQuestions()).thenReturn(List.of());

        ResponseStatusException exZero = assertThrows(
                ResponseStatusException.class,
                () -> examinerService.getQuestions(0)
        );
        ResponseStatusException exNegative = assertThrows(
                ResponseStatusException.class,
                () -> examinerService.getQuestions(-1)
        );

        assertEquals("Amount must be positive", exZero.getMessage());
        assertEquals("Amount must be positive", exNegative.getMessage());
    }
}
