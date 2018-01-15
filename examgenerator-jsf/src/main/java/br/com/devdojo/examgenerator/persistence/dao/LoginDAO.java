package br.com.devdojo.examgenerator.persistence.dao;

import br.com.devdojo.examgenerator.annotation.ExceptionHandler;
import br.com.devdojo.examgenerator.custom.CustomObjectMapper;
import br.com.devdojo.examgenerator.custom.CustomRestTemplate;
import br.com.devdojo.examgenerator.persistence.model.support.ErrorDetail;
import br.com.devdojo.examgenerator.persistence.model.support.Token;
import br.com.devdojo.examgenerator.util.ApiUtil;
import br.com.devdojo.examgenerator.util.JsonUtil;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.inject.Inject;
import java.io.IOException;
import java.io.Serializable;

public class LoginDAO implements Serializable {

    private final CustomRestTemplate mCustomRestTemplate;
    private final JsonUtil mJsonUtil;

    @Inject
    public LoginDAO(CustomRestTemplate customRestTemplate, JsonUtil jsonUtil) {
        mCustomRestTemplate = customRestTemplate;
        mJsonUtil = jsonUtil;
    }

    @ExceptionHandler
    public Token loginReturningToken(String username, String password) {

        String loginJson = "{\"username\":" + addQuotes(username) + ",\"password\":" + addQuotes(password) + "}";
        ResponseEntity<Token> tokenResponseEntity = mCustomRestTemplate
                .exchange(ApiUtil.LOGIN_URL, HttpMethod.POST, new HttpEntity<>(loginJson, mJsonUtil.createJsonHeader()), Token.class);

        return tokenResponseEntity.getBody();
    }

    private String addQuotes(String value) {
        return new StringBuilder(300).append("\"").append(value).append("\"").toString();
    }



}
