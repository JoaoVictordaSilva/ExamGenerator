package br.com.devdojo.examgenerator.persistence.repository;

import br.com.devdojo.examgenerator.persistence.model.Question;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends CustomPagingAndSortRepository<Question, Long> {

    @Query("select q from Question q where q.description like  %?1% and q.course.professor = ?#{principal.professor}")
    List<Question> listQuestion(String description);

}
