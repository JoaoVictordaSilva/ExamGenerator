package br.com.devdojo.examgenerator.persistence.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDate;

public class Token implements Serializable{

    private String token;
    @JsonProperty("exp")
    private LocalDate expirationTime;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDate getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDate expirationTime) {
        this.expirationTime = expirationTime;
    }
}
