package am.jsl.dolarek.dto.account;

import java.io.Serializable;
import org.springframework.format.annotation.NumberFormat;

/**
 * Używany do przenoszenia wyrównania salda konta z sieci.
 */
public class AdjustBalanceDTO implements Serializable {

  /**
   * Identyfikator konta
   */
  private long id;

  /**
   * Równowaga do ustawiania
   */
  @NumberFormat(pattern = "# ### ### ##0.00")
  private double balance;

  /**
   * Identyfikator użytkownika konta
   */
  private long userId;

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
   * Gets the value of the balance property.
   */
  public double getBalance() {
    return balance;
  }

  /**
   * Sets the balance of the item.
   *
   * @param balance - the balance of the item.
   */
  public void setBalance(double balance) {
    this.balance = balance;
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
}
