package am.jsl.dolarek.dto.user;

import am.jsl.dolarek.domain.user.Role;
import am.jsl.dolarek.domain.user.User;
import am.jsl.dolarek.util.Constants;
import am.jsl.dolarek.util.TextUtils;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Typ UserDTO
 */
public class UserDTO implements Serializable {

  private static final long serialVersionUID = 8416849345869102122L;

  /**
   * Wewnętrzny identyfikator
   */
  private long id;

  /**
   * Logowanie użytkownika
   */
  private String login;

  /**
   * Imię użytkownika
   */
  private String firstName;

  /**
   * Nazwisko użytkownika
   */
  private String lastName;

  /**
   * E -mail użytkownika
   */
  private String email;

  /**
   * Telefon użytkownika
   */
  private String phone;

  /**
   * Ikona użytkownika
   */
  private String icon = null;

  /**
   * Hasło użytkownika
   */
  private String password;

  /**
   * potwierdzoneHasłoUżytkownika
   */
  private String confirmPassword;

  /**
   * Wskazuje, czy ten użytkownik jest włączony
   */
  private boolean enabled;

  /**
   * Rola tego użytkownika
   */
  private Role role;

  /**
   * Ostatnio zalogowany w dniu
   */
  private LocalDateTime lastLogin = null;

  /**
   * Gets id.
   *
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets login.
   *
   * @return the login
   */
  public String getLogin() {
    return login;
  }

  /**
   * Sets login.
   *
   * @param login the login
   */
  public void setLogin(String login) {
    this.login = login;
  }

  /**
   * Gets first name.
   *
   * @return the first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets first name.
   *
   * @param firstName the first name
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Gets last name.
   *
   * @return the last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets last name.
   *
   * @param lastName the last name
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Gets email.
   *
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets email.
   *
   * @param email the email
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets phone.
   *
   * @return the phone
   */
  public String getPhone() {
    return phone;
  }

  /**
   * Sets phone.
   *
   * @param phone the phone
   */
  public void setPhone(String phone) {
    this.phone = phone;
  }

  /**
   * Gets icon.
   *
   * @return the icon
   */
  public String getIcon() {
    return icon;
  }

  /**
   * Sets icon.
   *
   * @param icon the icon
   */
  public void setIcon(String icon) {
    this.icon = icon;
  }

  /**
   * Gets password.
   *
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets password.
   *
   * @param password the password
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Gets confirm password.
   *
   * @return the confirm password
   */
  public String getConfirmPassword() {
    return confirmPassword;
  }

  /**
   * Sets confirm password.
   *
   * @param confirmPassword the confirm password
   */
  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

  /**
   * Jest włączony boolean
   *
   * @return boolean
   */
  public boolean isEnabled() {
    return enabled;
  }

  /**
   * Sets enabled.
   *
   * @param enabled the enabled
   */
  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  /**
   * Gets role.
   *
   * @return the role
   */
  public Role getRole() {
    return role;
  }

  /**
   * Sets role.
   *
   * @param role the role
   */
  public void setRole(Role role) {
    this.role = role;
  }

  /**
   * Gets last login.
   *
   * @return the last login
   */
  public LocalDateTime getLastLogin() {
    return lastLogin;
  }

  /**
   * Sets last login.
   *
   * @param lastLogin the last login
   */
  public void setLastLogin(LocalDateTime lastLogin) {
    this.lastLogin = lastLogin;
  }

  /**
   * Otrzymuje pełną ścieżkę ikony użytkownika
   *
   * @return ścieżka ikony
   */
  public String getIconPath() {
    if (!TextUtils.isEmpty(icon)) {
      StringBuilder path = new StringBuilder();
      path.append(Constants.USER_IMG_PATH).append(id);
      path.append(Constants.SLASH).append(icon);
      return path.toString();
    }
    return Constants.USER_PROFILE_DEFAULT_IMG;
  }

  /**
   * Tworzy obiekt userDTO z obiektu domeny użytkownika
   *
   * @param user użytkownik
   * @return  UserDTO
   */
  public static UserDTO from(User user) {
    UserDTO userDTO = new UserDTO();
    userDTO.setId(user.getId());
    userDTO.setLogin(user.getUsername());
    userDTO.setFirstName(user.getFirstName());
    userDTO.setLastName(user.getLastName());
    userDTO.setEmail(user.getEmail());
    userDTO.setPhone(user.getPhone());
    userDTO.setIcon(user.getIcon());
    userDTO.setEnabled(user.isEnabled());
    userDTO.setRole(user.getRole());
    return userDTO;
  }

  /**
   * Konwertuje userDTO na obiekt domeny użytkownika.
   *
   * @return obiekt domeny użytkownika
   */
  public User toUser() {
    User user = new User();
    user.setId(id);
    user.setLogin(login);
    user.setPassword(password);
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setEmail(email);
    user.setPhone(phone);
    user.setIcon(icon);
    user.setEnabled(enabled);
    user.setRole(role);
    return user;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, login, email);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    final UserDTO other = (UserDTO) obj;
    return (
      Objects.equals(this.id, other.id) &&
      Objects.equals(this.login, other.login) &&
      Objects.equals(this.email, other.email)
    );
  }
}
