package engine.services;

import engine.entity.Quiz;
import engine.entity.Submission;
import engine.repositories.QuizRepository;
import engine.repositories.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private SubmissionRepository submissionRepository;



    @Override
    public Page<Quiz> getPagedAllQuizzes(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return quizRepository.findAll(pageable);
    }


    public Page<Submission> getPagedSubmissions(int pageNo, int pageSize, Authentication authentication) {
        String username = authentication.getName();
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("timestamp").descending());
        return submissionRepository.getSubmissions(username, paging);


    }

    @Override
    public Quiz getQuizById(int id) {
        return quizRepository.findById(id).get();
    }

    @Override
    public Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public void deleteQuizById(int id) {
        quizRepository.deleteById(id);
    }


}
