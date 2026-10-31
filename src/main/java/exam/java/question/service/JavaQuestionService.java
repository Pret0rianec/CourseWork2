package exam.java.question.service;

import exam.java.question.model.Question;
import exam.java.question.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@Primary
public class JavaQuestionService implements QuestionService {

    private final QuestionRepository repository;
    private final Random random = new Random();

    @Autowired
    public JavaQuestionService(@Qualifier("javaQuestionRepository") QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addQuestion(Question question) {repository.add(question);}

    @Override
    public void removeQuestion(Question question) {
        repository.remove(question);
    }

    @Override
    public List<Question> getAllQuestions() {
        return repository.getAll();
    }

    public Question getRandomQuestion() {
        List<Question> all = getAllQuestions();
        if (all.isEmpty()) {
            return null;
        }
        int index = random.nextInt(all.size());
        return all.get(index);
    }
}
