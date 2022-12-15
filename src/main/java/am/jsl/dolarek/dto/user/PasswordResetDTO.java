package am.jsl.dolarek.dto.user;

import java.io.Serializable;

/**
 * PasswordResetDTO służy do resetowania (przez e-mail) hasła ze strony logowania.
 */
public class PasswordResetDTO implements Serializable {

  /**
   * Identyfikator użytkownika
   */
  private long userId;

  /**
   * Login użytkownika
   */
  private String login;

  /**
   * Token weryfikacyjny
   */
  private String token;

  /**
   * Nowe hasło
   */
  private String newPassword;

  /**
   * Ponownie wprowadzone nowe hasło
   */
  private String reNewPassword;

  /**
   * Getter for property 'userId'.
   *
   * @return Value for property 'userId'.
   */
  public long getUserId() {
    return userId;
  }

  /**
   * Setter for property 'userId'.
   *
   * @param userId Value to set for property 'userId'.
   */
  public void setUserId(long userId) {
    this.userId = userId;
  }

  /**
   * Getter for property 'token'.
   *
   * @return Value for property 'token'.
   */
  public String getToken() {
    return token;
  }

  /**
   * Setter for property 'token'.
   *
   * @param token Value to set for property 'token'.
   */
  public void setToken(String token) {
    this.token = token;
  }

  /**
   * Getter for property 'newPassword'.
   *
   * @return Value for property 'newPassword'.
   */
  public String getNewPassword() {
    return newPassword;
  }

  /**
   * Setter for property 'newPassword'.
   *
   * @param newPassword Value to set for property 'newPassword'.
   */
  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }

  /**
   * Getter for property 'reNewPassword'.
   *
   * @return Value for property 'reNewPassword'.
   */
  public String getReNewPassword() {
    return reNewPassword;
  }

  /**
   * Setter for property 'reNewPassword'.
   *
   * @param reNewPassword Value to set for property 'reNewPassword'.
   */
  public void setReNewPassword(String reNewPassword) {
    this.reNewPassword = reNewPassword;
  }

  /**
   * Getter for property 'login'.
   *
   * @return Value for property 'login'.
   */
  public String getLogin() {
    return login;
  }

  /**
   * Setter for property 'login'.
   *
   * @param login Value to set for property 'login'.
   */
  public void setLogin(String login) {
    this.login = login;
  }
}
