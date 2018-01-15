package br.com.devdojo.examgenerator.endpoint.v1;

import br.com.devdojo.examgenerator.persistence.model.Professor;

import static org.junit.Assert.*;

public class ProfessorEndpointTest {

    public static Professor mockProfessor(){
        return Professor.ProfessorBuilder.newProfessor()
                .id(1L)
                .name("JoaoVictor")
                .email("testandoemail@testeGmail.com")
                .build();
    }

}