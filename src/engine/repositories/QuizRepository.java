package engine.repositories;


import engine.entity.Quiz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuizRepository extends CrudRepository<Quiz, Integer>, PagingAndSortingRepository<Quiz, Integer> {


   @Override
   List<Quiz> findAll();


    @Override
    Quiz save(Quiz quiz);
}
