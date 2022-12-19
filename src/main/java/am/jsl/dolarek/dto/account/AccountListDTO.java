package am.jsl.dolarek.dto.account;

import am.jsl.dolarek.dto.DescriptiveDTO;

/**
 * Służy do przesyłania i wyświetlania szczegółów konta na stronach z listą kont.
 */
public class AccountListDTO extends DescriptiveDTO {

  /**
   * Bieżące saldo tego konta.
   */
  private double balance;

  /**
   * Rodzaj tego konta.
   */
  private byte accountType;

  /**
   *  Kod waluty ISO 4217 tego konta.
   */
  private String currency;

  /**
   * Natywny symbol waluty
   */
  private String symbol;

  /**
   * Status konta.To prawda, jeśli jest aktywny.
   */
  private boolean active;

  /**
   * Ikona tego konta.
   */
  private String icon;

  /**
   * Kolor tego konta.
   */
  private String color;

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
}
