package br.com.devdojo.examgenerator.endpoint.v1.question;

import br.com.devdojo.examgenerator.endpoint.v1.AbstractEndpoint;
import br.com.devdojo.examgenerator.persistence.model.Question;
import br.com.devdojo.examgenerator.persistence.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/professor/course/question")
public class QuestionEndpoint extends AbstractEndpoint<Question, QuestionRepository> {

    @Autowired
    public QuestionEndpoint(QuestionRepository questionRepository) {
        super(questionRepository);
    }

    @RequestMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return super.getEntityById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Question question) {
        return super.create(question);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        return super.delete(id);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Question question) {
        return super.update(question);
    }



}
