package br.com.devdojo.examgenerator.persistence.dao;

import br.com.devdojo.examgenerator.annotation.ExceptionHandler;
import br.com.devdojo.examgenerator.persistence.model.Professor;
import br.com.devdojo.examgenerator.util.ApiUtil;
import br.com.devdojo.examgenerator.util.JsonUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.io.Serializable;

public class ProfessorDAO implements Serializable{

    private final String BASE_URL = "http://localhost:8085/v1/professor";
    private final JsonUtil mJsonUtil;

    @Inject
    public ProfessorDAO(JsonUtil jsonUtil) {
        this.mJsonUtil = jsonUtil;
    }

    @ExceptionHandler
    public Professor getProfessorById(long id){
        ResponseEntity<Professor> professorResponseEntity = new RestTemplate()
                .exchange(ApiUtil.PROFESSOR_URL+ "/1", HttpMethod.GET, new HttpEntity<>(mJsonUtil.createTokenizedHeader()), Professor.class);

        return professorResponseEntity.getBody();
    }


}
