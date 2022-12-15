package am.jsl.dolarek.domain.user;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * Token weryfikacyjny jest wysyłany na adres e-mail użytkownika podczas tworzenia użytkowników lub resetowania haseł.
 * Wygaśnie w ciągu 24 godzin po utworzeniu.
 */
public class VerificationToken {

  /**
   * Czas wygaśnięcia tokenu weryfikacji (24 godziny)
   */
  private static final int EXPIRATION = 60 * 24;

  /**
   * Wewnętrzny identyfikator
   */
  private long id;

  /**
   * Unikalny, losowo wygenerowany token
   */
  private String token;

  /**
   *Typ tokena (NEW_ACCOUNT, PASSWORD_RESET)
   */
  private byte tokenType;

  /**
   * Identyfikator użytkownika powiązany z tym tokenem weryfikacyjnym
   */
  private long userId;

  /**
   * Data ważności tego tokena weryfikacji
   */
  private Date expiryDate;

  /**
   * Wskazuje, czy ten token weryfikacyjny wygasł
   */
  private boolean expired = false;

  /**
   * Domyślny konstruktor
   */
  public VerificationToken() {
    super();
  }

  /**
   * Oblicza datę ważności od daty bieżącej.
   *
   * @param expiryTimeInMinutes Czas wygaśnięcia w minutach
   * @return Data wygaśnięcia
   */
  private Date calculateExpiryDate(final int expiryTimeInMinutes) {
    final Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(new Date().getTime());
    cal.add(Calendar.MINUTE, expiryTimeInMinutes);
    return new Date(cal.getTime().getTime());
  }

  /**
   * Ustawia token i oblicza datę ważności tego tokena weryfikacyjnego.
   *
   * @param token Token do ustawienia
   */
  public void updateToken(final String token) {
    this.token = token;
    this.expiryDate = calculateExpiryDate(EXPIRATION);
    this.expired = false;
  }

  /**
   * Gets the value of the id property.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the id of the object.
   *
   * @param id - The new value of id.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets the value of the token property.
   */
  public String getToken() {
    return token;
  }

  /**
   * Sets the token of the token.
   *
   * @param token - The token to set.
   */
  public void setToken(String token) {
    this.token = token;
  }

  /**
   * Gets the token type of the token.
   */
  public byte getTokenType() {
    return tokenType;
  }

  /**
   * Sets the token type of the token.
   *
   * @param tokenType - The token type of the token.
   */
  public void setTokenType(byte tokenType) {
    this.tokenType = tokenType;
  }

  /**
   * Gets the value of the userId property.
   */
  public long getUserId() {
    return userId;
  }

  /**
   * Sets the userId value for this User.
   *
   * @param userId - The userId of the user to be used for the user.
   */
  public void setUserId(long userId) {
    this.userId = userId;
  }

  /**
   * Gets the value of the expiryDate property.
   */
  public Date getExpiryDate() {
    return expiryDate;
  }

  /**
   * Sets the expiryDate attribute value.
   *
   * @param expiryDate - The expiryDate to set.
   */
  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }

  /**
   * Gets the value of the expired property.
   */
  public boolean isExpired() {
    return expired;
  }

  /**
   * Sets the expired state of the object.
   *
   * @param expired - true if the user has expired the session.
   */
  public void setExpired(boolean expired) {
    this.expired = expired;
  }

  /**
   * Returns a hash code for this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(id, token, tokenType, userId, expiryDate, expired);
  }

  /**
   * Compares this object with the given object.
   *
   * @param obj - the object to compare this object with.
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
    final VerificationToken other = (VerificationToken) obj;
    return (
      Objects.equals(this.id, other.id) &&
      Objects.equals(this.token, other.token) &&
      Objects.equals(this.tokenType, other.tokenType) &&
      Objects.equals(this.userId, other.userId) &&
      Objects.equals(this.expiryDate, other.expiryDate) &&
      Objects.equals(this.expired, other.expired)
    );
  }
}
