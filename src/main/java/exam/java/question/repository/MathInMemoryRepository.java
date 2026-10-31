package exam.java.question.repository;

import exam.java.question.model.Question;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("mathQuestionRepository")
public class MathInMemoryRepository implements QuestionRepository {
    private final List<Question> storage = new ArrayList<>();

    @Override
    public void add(Question question) {storage.add(question);}

    @Override
    public void remove(Question question) {storage.remove(question);}

    @Override
    public List<Question> getAll() {return new ArrayList<>(storage);}
}