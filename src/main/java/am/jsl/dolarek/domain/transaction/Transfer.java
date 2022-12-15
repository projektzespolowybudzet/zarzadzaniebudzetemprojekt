package am.jsl.dolarek.domain.transaction;

import java.io.Serializable;

/**
 * Obiekt domeny transferu.
 */
public class Transfer implements Serializable {

  /**
   * Wewnętrzny identyfikator
   */
  private long id;

  /**
   * Identyfikator transakcji
   */
  private long transactionId;

  /**
   * Identyfikator konta docelowego
   */
  private long targetAccountId = 0;

  /**
   * Konwertowana kwota, która zostanie przeniesiona na konto docelowe
   */
  private double convertedAmount = 0;

  /**
   * Kurs wymiany
   */
  private double rate = 0;

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
   * Gets the value of the transactionId property.
   */
  public long getTransactionId() {
    return transactionId;
  }

  /**
   * Sets the transactionId value for this InvoiceParamsOutC.
   *
   * @param transactionId - The transactionId of the transaction.
   */
  public void setTransactionId(long transactionId) {
    this.transactionId = transactionId;
  }

  /**
   * Gets the value of the targetAccountId property.
   */
  public long getTargetAccountId() {
    return targetAccountId;
  }

  /**
   * Sets the targetAccountId attribute value.
   *
   * @param targetAccountId - The targetAccountId to set.
   */
  public void setTargetAccountId(long targetAccountId) {
    this.targetAccountId = targetAccountId;
  }

  /**
   * Gets the value of the convertedAmount property.
   */
  public double getConvertedAmount() {
    return convertedAmount;
  }

  /**
   * Sets the amount of the amount to convert to.
   *
   * @param convertedAmount - The amount to convert to.
   */
  public void setConvertedAmount(double convertedAmount) {
    this.convertedAmount = convertedAmount;
  }

  /**
   * Gets the rate of the player.
   */
  public double getRate() {
    return rate;
  }

  /**
   * Sets the rate of the player.
   *
   * @param rate - the new rate of the player
   */
  public void setRate(double rate) {
    this.rate = rate;
  }
}
