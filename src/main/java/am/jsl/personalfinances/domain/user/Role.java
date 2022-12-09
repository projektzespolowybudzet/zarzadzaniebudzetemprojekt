package am.jsl.personalfinances.domain.user;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

/**
*Wyliczenie ról zawierające możliwe role.
*/
public enum Role implements Serializable, GrantedAuthority {

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
