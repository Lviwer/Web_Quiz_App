package engine.services;

import engine.entity.Quiz;
import engine.entity.Submission;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

public interface QuizService {

    //List<Quiz> getPagedAllQuizzes(Integer pageNo, Integer pageSize);
    Page<Quiz> getPagedAllQuizzes(Integer pageNo, Integer pageSize);

    Quiz getQuizById(int id);

    Quiz saveQuiz(Quiz quiz);

    void deleteQuizById(int id);

    public Page<Submission> getPagedSubmissions(int pageNo, int pageSize, Authentication authentication);
}
