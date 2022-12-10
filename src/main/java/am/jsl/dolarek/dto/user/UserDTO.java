package am.jsl.dolarek.dto.user;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import am.jsl.dolarek.domain.user.Role;
import am.jsl.dolarek.domain.user.User;
import am.jsl.dolarek.util.Constants;
import am.jsl.dolarek.util.TextUtils;

/**
*Typ UserDTO
*/
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 8416849345869102122L;
    private long id;
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String icon = null;
    private String password;
    private String confirmPassword;
    private boolean enabled;
    private Role role;
    private LocalDateTime lastLogin = null;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }
    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getIconPath() {
        if (!TextUtils.isEmpty(icon)) {
            StringBuilder path = new StringBuilder();
            path.append(Constants.USER_IMG_PATH).append(id);
            path.append(Constants.SLASH).append(icon);
            return path.toString();
        }
        return Constants.USER_PROFILE_DEFAULT_IMG;
    }

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
        return Objects.equals(this.id, other.id)
                && Objects.equals(this.login, other.login)
                && Objects.equals(this.email, other.email);
    }
}
