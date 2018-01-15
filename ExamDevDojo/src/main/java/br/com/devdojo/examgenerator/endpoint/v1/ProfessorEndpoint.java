package br.com.devdojo.examgenerator.endpoint.v1;


import br.com.devdojo.examgenerator.persistence.model.Professor;
import br.com.devdojo.examgenerator.persistence.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/professor")
public class ProfessorEndpoint extends AbstractEndpoint<Professor, ProfessorRepository> {

    @Autowired
    public ProfessorEndpoint(ProfessorRepository professorRepository) {
        super(professorRepository);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<?> getProfessorById(@PathVariable long id) {
        return super.getEntityById(id);
    }

}
