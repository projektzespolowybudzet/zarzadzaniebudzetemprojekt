package am.jsl.personalfinances.domain.user;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
*Token weryfikacyjny jest wysyłany na adres e-mail użytkownika podczas tworzenia użytkowników lub resetowania haseł.
*Wygaśnie w ciągu 24 godzin po utworzeniu.
*/
public class VerificationToken {

    private static final int EXPIRATION = 60 * 24;
    private long id;
    private String token;

    /**
     *(NEW_ACCOUNT, PASSWORD_RESET)
     */
    private byte tokenType;
    private long userId;
    private Date expiryDate;
    private boolean expired = false;

    public VerificationToken() {
        super();
    }

/**
*Oblicza datę ważności od daty bieżącej.
*@param expiryTimeInMinutes Czas wygaśnięcia w minutach
*@return Data wygaśnięcia
*/
    private Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

/**
*Ustawia token i oblicza datę ważności tego tokena weryfikacyjnego.
*@param token Token do ustawienia
*/
    public void updateToken(final String token) {
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
        this.expired = false;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public byte getTokenType() {
        return tokenType;
    }

    public void setTokenType(byte tokenType) {
        this.tokenType = tokenType;
    }

    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isExpired() {
        return expired;
    }
    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token, tokenType, userId, expiryDate, expired);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final VerificationToken other = (VerificationToken) obj;
        return Objects.equals(this.id, other.id)
                && Objects.equals(this.token, other.token)
                && Objects.equals(this.tokenType, other.tokenType)
                && Objects.equals(this.userId, other.userId)
                && Objects.equals(this.expiryDate, other.expiryDate)
                && Objects.equals(this.expired, other.expired);
    }
}
