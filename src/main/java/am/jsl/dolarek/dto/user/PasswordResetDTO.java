package am.jsl.dolarek.dto.user;

import java.io.Serializable;

/**
 *PasswordResetDTO służy do resetowania (przez e-mail) hasła ze strony logowania.
 */
public class PasswordResetDTO implements Serializable {

    private long userId;
    private String login;
    private String token;
    private String newPassword;
    private String reNewPassword;

    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public String getNewPassword() {
        return newPassword;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getReNewPassword() {
        return reNewPassword;
    }
    public void setReNewPassword(String reNewPassword) {
        this.reNewPassword = reNewPassword;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
}
