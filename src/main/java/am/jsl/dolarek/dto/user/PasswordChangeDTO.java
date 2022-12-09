package am.jsl.dolarek.dto.user;

import java.io.Serializable;

/**
*PasswordChangeDTO służy do zmiany hasła ze strony profilu użytkownika.
*/
public class PasswordChangeDTO implements Serializable {

    private long id;
    private String oldPassword;
    private String newPassword;
    private String rePassword;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getOldPassword() {
        return oldPassword;
    }
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRePassword() {
        return rePassword;
    }
    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
}
