package am.jsl.dolarek.dto.account;

import am.jsl.dolarek.domain.account.Account;
import am.jsl.dolarek.dto.DescriptiveDTO;
import org.springframework.format.annotation.NumberFormat;

/**
 * Służy do przesyłania danych konta między stronami internetowymi i kontrolerami.
 */
public class AccountDTO extends DescriptiveDTO {

  /**
   * Obecne saldo tego konta.
   */
  @NumberFormat(pattern = "# ### ### ##0.00")
  private double balance;

  /**
   * Trodzaj tego konta.
   */
  private byte accountType;

  /**
   * Kod waluty ISO 4217 tego konta.
   */
  private String currency;

  /**
   * Natywny symbol waluty
   */
  private String symbol;

  /**
   * Status konta.To prawda, jeśli jest aktywny.
   */
  private boolean active = true;

  /**
   * Ikona tego konta.
   */
  private String icon;

  /**
   * Kolor tego konta.
   */
  private String color;

  /**
   * Kolejność sortowania
   */
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
   * Returns a new Account object.
   */
  public Account toAccount() {
    Account account = new Account();
    account.setId(getId());
    account.setName(getName());
    account.setDescription(getDescription());
    account.setBalance(getBalance());
    account.setAccountType(getAccountType());
    account.setCurrency(getCurrency());
    account.setSymbol(getSymbol());
    account.setActive(isActive());
    account.setSortOrder(getSortOrder());
    account.setIcon(getIcon());
    account.setColor(getColor());
    return account;
  }

  /**
   * Creates a new link AccountDTO from the given link Account.
   *
   * @param account - the account to convert.
   */
  public static AccountDTO from(Account account) {
    AccountDTO dto = new AccountDTO();
    dto.setId(account.getId());
    dto.setName(account.getName());
    dto.setDescription(account.getDescription());
    dto.setBalance(account.getBalance());
    dto.setAccountType(account.getAccountType());
    dto.setCurrency(account.getCurrency());
    dto.setSymbol(account.getSymbol());
    dto.setActive(account.isActive());
    dto.setSortOrder(account.getSortOrder());
    dto.setIcon(account.getIcon());
    dto.setColor(account.getColor());
    return dto;
  }
}
