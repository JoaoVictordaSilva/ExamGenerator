package br.com.devdojo.examgenerator.util;

import br.com.devdojo.examgenerator.persistence.model.ApplicationUser;
import br.com.devdojo.examgenerator.persistence.model.Professor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class EndpointUtil implements Serializable {

    public Professor extractProfessorFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((ApplicationUser) authentication.getPrincipal()).getProfessor();
    }

}
