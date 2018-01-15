package br.com.devdojo.examgenerator.custom;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
// This class is responsible for injecting the resttemplate
public class CustomRestTemplate  extends RestTemplate{

    public CustomRestTemplate() {
        this.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }
}
