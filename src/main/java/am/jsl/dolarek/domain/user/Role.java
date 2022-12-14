package am.jsl.dolarek.domain.user;

import org.springframework.security.core.GrantedAuthority;

/**
 * Wyliczenie ról zawierające możliwe role.
 */
public enum Role implements GrantedAuthority {
  USER,
  ADMIN;

  /**
   * Returns the name of this property.
   */
  @Override
  public String toString() {
    return name();
  }

  /**
   * Returns the authority of the user.
   */
  @Override
  public String getAuthority() {
    return name();
  }
}
