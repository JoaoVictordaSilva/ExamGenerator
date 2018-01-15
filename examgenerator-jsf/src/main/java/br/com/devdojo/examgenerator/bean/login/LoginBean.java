package br.com.devdojo.examgenerator.bean.login;

import br.com.devdojo.examgenerator.custom.CustomURLEncoderDecoder;
import br.com.devdojo.examgenerator.persistence.dao.LoginDAO;
import br.com.devdojo.examgenerator.persistence.model.support.Token;

import javax.faces.context.ExternalContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;
    private final LoginDAO mLoginDAO;
    private final ExternalContext mExternalContext;

    @Inject
    public LoginBean(LoginDAO loginDAO, ExternalContext externalContext) {
        mLoginDAO = loginDAO;
        mExternalContext = externalContext;
    }

    public String login() {
        Token token = mLoginDAO.loginReturningToken(username, password);
        if (token == null) return null;
        addTokenAndExpirationTimeToCookies(token.getToken(), token.getExpirationTime().toString());

        return "index.xhtml?faces-redirect=true";
    }

    public String logout() {
        removeTokenAndExpirationTimeFromCookies();

        return "login.xhtml?faces-redirect=true";
    }


    private void addTokenAndExpirationTimeToCookies(String token, String expirationTime) {
        mExternalContext.addResponseCookie("token", CustomURLEncoderDecoder.encodeUTF8(token), null);
        mExternalContext.addResponseCookie("expirationTime", expirationTime, null);
    }

    private void removeTokenAndExpirationTimeFromCookies() {
        addTokenAndExpirationTimeToCookies(null, null);
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
