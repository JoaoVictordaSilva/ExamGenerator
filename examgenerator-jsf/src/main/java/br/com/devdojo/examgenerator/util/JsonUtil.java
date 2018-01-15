package br.com.devdojo.examgenerator.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.faces.annotation.RequestCookieMap;
import javax.inject.Inject;
import javax.servlet.http.Cookie;
import java.io.Serializable;
import java.util.Map;

import static br.com.devdojo.examgenerator.custom.CustomURLEncoderDecoder.decodeUTF8;

public class JsonUtil implements Serializable{

    @Inject
    @RequestCookieMap
    private Map<String, Object> mCookieMap;

    public HttpHeaders createJsonHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return headers;
    }

    public HttpHeaders createTokenizedHeader() {
        HttpHeaders headers = createJsonHeader();
        Cookie tokenCookie = (Cookie) mCookieMap.get("token");
        headers.add("Authorization", decodeUTF8(tokenCookie.getValue()));
        return headers;
    }

    public HttpEntity tokenizedHttpEntityHeader(){
        return new HttpEntity(createTokenizedHeader());
    }


    public <E> HttpEntity<E> tokenizedHttpEntityHeader(E e){
        return new HttpEntity<>(e, createTokenizedHeader());
    }

}
