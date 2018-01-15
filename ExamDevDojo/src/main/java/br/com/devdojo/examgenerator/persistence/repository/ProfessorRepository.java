package br.com.devdojo.examgenerator.persistence.repository;

import br.com.devdojo.examgenerator.persistence.model.Professor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProfessorRepository extends CustomPagingAndSortRepository<Professor, Long> {

    Professor findByEmail(String email);
}
