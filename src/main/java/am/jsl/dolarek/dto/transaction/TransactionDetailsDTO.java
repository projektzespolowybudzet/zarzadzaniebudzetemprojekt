package am.jsl.dolarek.dto.transaction;

/**
 * TransactionDetailsDTO służy do przesyłania szczegółowych informacji o transakcji.
 */
public class TransactionDetailsDTO extends BaseTransactionDTO {

  /**
   * Nazwa konta
   */
  private String account;

  /**
   * Ikona konta
   */
  private String accountIcon;

  /**
   * Kolor konta
   */
  private String accountColor;

  /**
   * Status transakcji
   */
  private byte status;

  /**
   * Identyfikator konta docelowego
   */
  private long targetAccountId = 0;

  /**
   * Nazwa konta docelowego
   */
  private String targetAccount;

  /**
   * Ikona konta docelowego dla typu transferu
   */
  private String targetAccountIcon;

  /**
   * Kolor konta docelowy dla typu transferu
   */
  private String targetAccountColor;

  /**
   * Natywny symbol waluty konta docelowego typu transferu
   */
  private String targetAccountSymbol;

  /**
   * Kurs waluty, który jest używany po obliczaniu konwersji
   */
  private double exchangeRate;

  /**
   * Konwertowana kwota
   */
  private double convertedAmount;

  /**
   * Getter for property 'account'.
   *
   * @return Value for property 'account'.
   */
  public String getAccount() {
    return account;
  }

  /**
   * Setter for property 'account'.
   *
   * @param account Value to set for property 'account'.
   */
  public void setAccount(String account) {
    this.account = account;
  }

  /**
   * Getter for property 'accountIcon'.
   *
   * @return Value for property 'accountIcon'.
   */
  public String getAccountIcon() {
    return accountIcon;
  }

  /**
   * Setter for property 'accountIcon'.
   *
   * @param accountIcon Value to set for property 'accountIcon'.
   */
  public void setAccountIcon(String accountIcon) {
    this.accountIcon = accountIcon;
  }

  /**
   * Getter for property 'accountColor'.
   *
   * @return Value for property 'accountColor'.
   */
  public String getAccountColor() {
    return accountColor;
  }

  /**
   * Setter for property 'accountColor'.
   *
   * @param accountColor Value to set for property 'accountColor'.
   */
  public void setAccountColor(String accountColor) {
    this.accountColor = accountColor;
  }

  /**
   * Getter for property 'status'.
   *
   * @return Value for property 'status'.
   */
  public byte getStatus() {
    return status;
  }

  /**
   * Setter for property 'status'.
   *
   * @param status Value to set for property 'status'.
   */
  public void setStatus(byte status) {
    this.status = status;
  }

  /**
   * Getter for property 'targetAccountId'.
   *
   * @return Value for property 'targetAccountId'.
   */
  public long getTargetAccountId() {
    return targetAccountId;
  }

  /**
   * Setter for property 'targetAccountId'.
   *
   * @param targetAccountId Value to set for property 'targetAccountId'.
   */
  public void setTargetAccountId(long targetAccountId) {
    this.targetAccountId = targetAccountId;
  }

  /**
   * Getter for property 'targetAccount'.
   *
   * @return Value for property 'targetAccount'.
   */
  public String getTargetAccount() {
    return targetAccount;
  }

  /**
   * Setter for property 'targetAccount'.
   *
   * @param targetAccount Value to set for property 'targetAccount'.
   */
  public void setTargetAccount(String targetAccount) {
    this.targetAccount = targetAccount;
  }

  /**
   * Getter for property 'targetAccountIcon'.
   *
   * @return Value for property 'targetAccountIcon'.
   */
  public String getTargetAccountIcon() {
    return targetAccountIcon;
  }

  /**
   * Setter for property 'targetAccountIcon'.
   *
   * @param targetAccountIcon Value to set for property 'targetAccountIcon'.
   */
  public void setTargetAccountIcon(String targetAccountIcon) {
    this.targetAccountIcon = targetAccountIcon;
  }

  /**
   * Getter for property 'targetAccountColor'.
   *
   * @return Value for property 'targetAccountColor'.
   */
  public String getTargetAccountColor() {
    return targetAccountColor;
  }

  /**
   * Setter for property 'targetAccountColor'.
   *
   * @param targetAccountColor Value to set for property 'targetAccountColor'.
   */
  public void setTargetAccountColor(String targetAccountColor) {
    this.targetAccountColor = targetAccountColor;
  }

  public String getTargetAccountSymbol() {
    return targetAccountSymbol;
  }

  public void setTargetAccountSymbol(String targetAccountSymbol) {
    this.targetAccountSymbol = targetAccountSymbol;
  }

  /**
   * Getter for property 'exchangeRate'.
   *
   * @return Value for property 'exchangeRate'.
   */
  public double getExchangeRate() {
    return exchangeRate;
  }

  /**
   * Setter for property 'exchangeRate'.
   *
   * @param exchangeRate Value to set for property 'exchangeRate'.
   */
  public void setExchangeRate(double exchangeRate) {
    this.exchangeRate = exchangeRate;
  }

  /**
   * Getter for property 'convertedAmount'.
   *
   * @return Value for property 'convertedAmount'.
   */
  public double getConvertedAmount() {
    return convertedAmount;
  }

  /**
   * Setter for property 'convertedAmount'.
   *
   * @param convertedAmount Value to set for property 'convertedAmount'.
   */
  public void setConvertedAmount(double convertedAmount) {
    this.convertedAmount = convertedAmount;
  }
}
