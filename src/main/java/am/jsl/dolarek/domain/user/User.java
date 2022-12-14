package am.jsl.dolarek.domain.user;

import am.jsl.dolarek.domain.BaseEntity;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Obiekt domeny użytkownika, który implementuje UserDetails i będzie używany
 * Spring Security dla celów bezpieczeństwa.
 */
public class User extends BaseEntity implements UserDetails {

  private String login;
  private String password;
  private String firstName;
  private String lastName;
  private String email;
  private String phone;
  private String icon = null;
  private LocalDateTime lastLogin = null;
  private boolean enabled = true;
  private Role role;
  private Set<GrantedAuthority> authorities = null;

  public User() {
    super();
  }

  /**
   * Gets the value of the login property.
   */
  public String getLogin() {
    return login;
  }

  /**
   * Sets the login value for this ServiceContext.
   *
   * @param login - The login to set.
   */
  public void setLogin(String login) {
    this.login = login;
  }

  /**
   * Returns the password of the user.
   */
  @Override
  public String getPassword() {
    return password;
  }

  /**
   * Returns the username of the user.
   */
  @Override
  public String getUsername() {
    return getLogin();
  }

  /**
   * Sets the password for the user.
   *
   * @param password - The password to use for the authentication.
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Gets the value of the firstName property.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets the firstName attribute value.
   *
   * @param firstName - The firstName of the first name.
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Gets the value of the lastName property.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets the value of LastName.
   *
   * @param lastName - The lastName of the user.
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Gets the value of the email property.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the value of field email.
   *
   * @param email - The email address of the user.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets the value of the phone property.
   */
  public String getPhone() {
    return phone;
  }

  /**
   * Sets the phone number of the user.
   *
   * @param phone - New value of property phone.
   */
  public void setPhone(String phone) {
    this.phone = phone;
  }

  /**
   * Gets the value of the lastLogin property.
   */
  public LocalDateTime getLastLogin() {
    return lastLogin;
  }

  /**
   * Sets the value of LastLogin.
   *
   * @param lastLogin - The lastLogin value to set.
   */
  public void setLastLogin(LocalDateTime lastLogin) {
    this.lastLogin = lastLogin;
  }

  /**
   * Sets the enabled flag.
   *
   * @param enabled - true if the user is enabled
   */
  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  /**
   * Gets the value of the icon property.
   */
  public String getIcon() {
    return icon;
  }

  /**
   * Sets the icon of the image.
   *
   * @param icon - the icon to use for the icon
   */
  public void setIcon(String icon) {
    this.icon = icon;
  }

  /**
   * Gets the value of the role property.
   */
  public Role getRole() {
    return role;
  }

  /**
   * Sets the role of the role.
   *
   * @param role - The role to set.
   */
  public void setRole(Role role) {
    this.role = role;
  }

  /**
   * Returns a hash code for this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(getId(), login);
  }

  /**
   * Compares this User and another User.
   *
   * @param obj - the object to compare with this object.
   */
  @Override
  public boolean equals(Object obj) {
    /**
     *Returns true if this object is the same as this object.
     */
    if (this == obj) {
      return true;
    }
    /**
     *Returns true if the object is null or if the class is not the same as the object.
     */
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    final User other = (User) obj;
    return (
      Objects.equals(this.getId(), other.getId()) &&
      Objects.equals(this.login, other.login)
    );
  }

  /**
   * Returns the authorities of the role.
   */
  @Override
  public Collection<GrantedAuthority> getAuthorities() {
    /**
     *Add the role to the list of authorities.
     */
    if (authorities == null) {
      authorities = new HashSet<>();
      authorities.add(role);
    }

    return authorities;
  }

  /**
   * Returns true if account is valid not expired
   */
  @Override
  public boolean isAccountNonExpired() {
    //return true = account is valid / not expired
    return true;
  }

  /**
   * Returns true if the account is not locked.
   */
  @Override
  public boolean isAccountNonLocked() {
    //return true = account is not locked
    return true;
  }

  /**
   * Returns true if the password is valid not expired
   */
  @Override
  public boolean isCredentialsNonExpired() {
    //return true = password is valid / not expired
    return true;
  }

  /**
   * Returns true if the user is enabled.
   */
  @Override
  public boolean isEnabled() {
    return enabled;
  }

  /**
   * Returns a string representation of this user.
   */
  @Override
  public String toString() {
    return (
      "User{" +
      "id='" +
      getId() +
      '\'' +
      "login='" +
      login +
      '\'' +
      ", firstName='" +
      firstName +
      '\'' +
      ", lastName='" +
      lastName +
      '\'' +
      ", email='" +
      email +
      '\'' +
      ", phone='" +
      phone +
      '\'' +
      ", enabled=" +
      enabled +
      ", role=" +
      role +
      '}'
    );
  }
}
