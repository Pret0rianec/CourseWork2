package exam.java.question.test;

import exam.java.question.model.Question;
import exam.java.question.repository.JavaInMemoryRepository;
import exam.java.question.repository.QuestionRepository;
import exam.java.question.service.JavaQuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {

    private JavaQuestionService service;
    private QuestionRepository repository;

    @BeforeEach
    void setUp() {
        service = new JavaQuestionService(repository);
    }

    @Test
    void testAddAndGetAll() {
        Question q1 = new Question("What is Java?", "Java is a programming language.");
        Question q2 = new Question("What is JVM?", "JVM is Java Virtual Machine.");

        service.addQuestion(q1);
        service.addQuestion(q2);

        List<Question> all = service.getAllQuestions();
        assertEquals(2, all.size());
        assertTrue(all.contains(q1));
        assertTrue(all.contains(q2));
    }

    @Test
    void testRemove() {
        Question q = new Question("Q1", "A1");
        service.addQuestion(q);
        assertEquals(1, service.getAllQuestions().size());

        service.removeQuestion(q);
        assertEquals(0, service.getAllQuestions().size());
    }

    @Test
    void testGetRandomQuestion_NotEmpty() {
        Question q = new Question("Random Q", "Random A");
        service.addQuestion(q);
        Question random = service.getRandomQuestion();
        assertNotNull(random);
        assertEquals(q.getQuestion(), random.getQuestion());
    }

    @Test
    void testGetRandomQuestion_Empty() {
        assertNull(service.getRandomQuestion());
    }

    @Test
    void testAddAndGetAll_ViaRepository() {
        JavaInMemoryRepository repo = new JavaInMemoryRepository();
        JavaQuestionService service = new JavaQuestionService(repo);
        Question q1 = new Question("What is JVM?", "Java Virtual Machine");
        service.addQuestion(q1);
        var all = service.getAllQuestions();
        assertEquals(1, all.size());
        assertTrue(all.contains(q1));
    }
}
