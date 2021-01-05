package engine.entity;

import engine.entity.Assessment;
import engine.entity.Quiz;

import java.util.List;

public class Revise {

    private Quiz quiz;

    public Revise(Quiz quiz) {
        this.quiz = quiz;
    }

    public Assessment getResponseQuiz(List<Integer> answer) {
        if (quiz.getAnswer().toString().equals(answer.toString())) {
            return new Assessment(true);
        } else {
            return new Assessment(false);
        }
    }
}
