package am.jsl.dolarek.domain.account;

import am.jsl.dolarek.domain.Descriptive;
import java.util.Objects;

/**
 * Obiekt domeny konta.
 */
public class Account extends Descriptive {

  private double balance;
  /**
   * @see AccountType
   */
  private byte accountType;
  private String currency;
  private String symbol;
  private boolean active = true;
  private String icon;
  private String color;
  private int sortOrder;

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
   * Gets the value of the accountType property.
   */
  public byte getAccountType() {
    return accountType;
  }

  /**
   * Sets the accountType attribute value.
   *
   * @param accountType - The accountType to set.
   */
  public void setAccountType(byte accountType) {
    this.accountType = accountType;
  }

  /**
   * Gets the value of the currency property.
   */
  public String getCurrency() {
    return currency;
  }

  /**
   * Sets the currency of the currency.
   *
   * @param currency - The currency to set.
   */
  public void setCurrency(String currency) {
    this.currency = currency;
  }

  /**
   * Gets the value of the symbol property.
   */
  public String getSymbol() {
    return symbol;
  }

  /**
   * Sets the symbol of the symbol.
   *
   * @param symbol - The symbol of the symbol.
   */
  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  /**
   * Returns true if the user is currently active.
   */
  public boolean isActive() {
    return active;
  }

  /**
   * Sets the active state of the player.
   *
   * @param active - true if the user is active
   */
  public void setActive(boolean active) {
    this.active = active;
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
   * Gets the value of the color property.
   */
  public String getColor() {
    return color;
  }

  /**
   * Sets the color of the color.
   *
   * @param color - The color of the color.
   */
  public void setColor(String color) {
    this.color = color;
  }

  /**
   * Gets the value of the sortOrder property.
   */
  public int getSortOrder() {
    return sortOrder;
  }

  /**
   * Sets the sort order of the list.
   *
   * @param sortOrder - The sort order of the search results.
   */
  public void setSortOrder(int sortOrder) {
    this.sortOrder = sortOrder;
  }

  /**
   * Compares this account with the given object.
   *
   * @param o - the object to compare this account and its name.
   */
  @Override
  public boolean equals(Object o) {
    /**
     * Returns true if this object is the same as the receiver.
     */
    if (this == o) return true;
    /**
     * Returns true if the object is null.
     */
    if (o == null) return false;

    /**
     * Checks if the account is the same as the id and name.
     */
    if (o instanceof Account) {
      final Account other = (Account) o;
      return (
        Objects.equals(getId(), other.getId()) &&
        Objects.equals(getName(), other.getName())
      );
    }
    return false;
  }

  /**
   * Returns a hash code for this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName());
  }
}
