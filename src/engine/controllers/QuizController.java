package engine.controllers;

import engine.entity.*;
import engine.repositories.SubmissionRepository;
import engine.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.NoSuchElementException;

@RestController
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private SubmissionRepository submissionRepository;


    @PostMapping("/api/quizzes")
    public Quiz addQuiz(@AuthenticationPrincipal User user,
                        @RequestBody @Valid Quiz quiz) {
        try {
            quiz.setUser(user);
            return quizService.saveQuiz(quiz);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/api/quizzes")
    public Page<Quiz> findAll(@RequestParam(defaultValue = "0") @Min(0) Integer page,
                              @RequestParam(defaultValue = "10") @Min(1) Integer pageSize) {
        return quizService.getPagedAllQuizzes(page, pageSize);
    }


    @GetMapping(path = "api/quizzes/completed")
    public Page<Submission> getCompletedQuizzess(@RequestParam(required = false, defaultValue = "0") @Min(0) Integer page,
                                                 @RequestParam(required = false, defaultValue = "10") @Min(1) Integer pageSize,
                                                 Authentication authentication) {
        return quizService.getPagedSubmissions(page, pageSize, authentication);
    }


    @GetMapping(path = "api/quizzes/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable int id) {
        Quiz quiz;
        try {
            quiz = quizService.getQuizById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such quiz");
        }
        return new ResponseEntity<>(quiz, HttpStatus.OK);
    }

    @PostMapping("/api/quizzes/{id}/solve")
    public Assessment responseQuiz(@PathVariable int id,
                                   @RequestBody Answer answer,
                                   Authentication authentication) {

        if (new Revise(quizService.getQuizById(id)).getResponseQuiz(answer.getAnswer()).isSuccess()) {
            Submission submission = new Submission(authentication.getName(), id, System.currentTimeMillis());
            submissionRepository.save(submission);
        }
        return new Revise(quizService.getQuizById(id)).getResponseQuiz(answer.getAnswer());


    }


    @DeleteMapping("/api/quizzes/{id}")
    public ResponseEntity<Quiz> responseEntity(@AuthenticationPrincipal User user,
                                               @PathVariable int id) {
        Quiz quiz;
        try {
            quiz = quizService.getQuizById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such quiz");
        }
        if (user.getId() == quiz.getUser().getId()) {
            quizService.deleteQuizById(id);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

}

