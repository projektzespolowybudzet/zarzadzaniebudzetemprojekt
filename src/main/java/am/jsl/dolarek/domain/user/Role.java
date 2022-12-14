package am.jsl.dolarek.domain.user;

import org.springframework.security.core.GrantedAuthority;

/**
*Wyliczenie ról zawierające możliwe role.
*/
public enum Role implements GrantedAuthority {

	USER,
	ADMIN;

	@Override
	public String toString(){
		return name();
	}

	@Override
	public String getAuthority() {
		return name();
	}
}
