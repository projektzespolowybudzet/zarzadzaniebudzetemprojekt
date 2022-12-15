package am.jsl.dolarek.domain.transaction;

import am.jsl.dolarek.domain.Descriptive;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Obiekt domeny transakcji.
 */
public class Transaction extends Descriptive {

  /**
   * Identyfikator konta
   */
  private long accountId;

  /**
   * Identyfikator kategorii
   */
  private long categoryId;

  /**
   * Ilość
   */
  private double amount;

  /**
   * Status
   * @see TransactionStatus
   */
  private byte status;

  /**
   * Typ transakcji
   */
  private byte transactionType;

  /**
   * Źródło transakcji
   * @see TransactionSource
   */
  private byte transactionSource = TransactionSource.MANUAL.getValue();

  /**
   * Data transakcji
   */
  private LocalDateTime transactionDate;

  /**
   * Przekazuje szczegóły typu transferu
   * @see Transfer
   */
  private Transfer transfer = null;

  /**
   * Zwraca true, jeśli typ transakcji, jeśli przeniesienie
   *
   * @return true jeśli typ transakcji, jeśli przeniesienie
   */
  public boolean isTransferType() {
    return transactionType == TransactionType.TRANSFER.getValue();
  }

  /**
   * Gets the value of the transfer property.
   */
  public Transfer getTransfer() {
    return transfer;
  }

  /**
   * Sets the transfer of the transfer.
   *
   * @param transfer - The transfer to set.
   */
  public void setTransfer(Transfer transfer) {
    this.transfer = transfer;
  }

  /**
   * Gets the value of the accountId property.
   */
  public long getAccountId() {
    return accountId;
  }

  /**
   * Sets the accountId value for this InvoiceParamsOutC.
   *
   * @param accountId - The accountId of the account to associate with this account.
   */
  public void setAccountId(long accountId) {
    this.accountId = accountId;
  }

  /**
   * Gets the value of the categoryId property.
   */
  public long getCategoryId() {
    return categoryId;
  }

  /**
   * Sets the value of the categoryId property.
   *
   * @param categoryId - The id of the category to set.
   */
  public void setCategoryId(long categoryId) {
    this.categoryId = categoryId;
  }

  /**
   * Gets the value of the amount property.
   */
  public double getAmount() {
    return amount;
  }

  /**
   * Sets the amount of the amount.
   *
   * @param amount - The amount to set.
   */
  public void setAmount(double amount) {
    this.amount = amount;
  }

  /**
   * Gets the value of the status field.
   */
  public byte getStatus() {
    return status;
  }

  /**
   * Sets the status of the message.
   *
   * @param status - The status of the message.
   */
  public void setStatus(byte status) {
    this.status = status;
  }

  /**
   * Gets the value of the transactionType property.
   */
  public byte getTransactionType() {
    return transactionType;
  }

  /**
   * Sets the transactionType attribute value.
   *
   * @param transactionType - The transactionType to set.
   */
  public void setTransactionType(byte transactionType) {
    this.transactionType = transactionType;
  }

  /**
   * Gets the value of the transactionDate property.
   */
  public LocalDateTime getTransactionDate() {
    return transactionDate;
  }

  /**
   * Sets the transactionDate attribute value.
   *
   * @param transactionDate - The transactionDate to set.
   */
  public void setTransactionDate(LocalDateTime transactionDate) {
    this.transactionDate = transactionDate;
  }

  /**
   * Gets the value of the transactionSource property.
   */
  public byte getTransactionSource() {
    return transactionSource;
  }

  /**
   * Sets the transactionSource attribute value.
   *
   * @param transactionSource - The transactionSource to set.
   */
  public void setTransactionSource(byte transactionSource) {
    this.transactionSource = transactionSource;
  }

  /**
   * Returns a hash code for this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }

  /**
   * Compares this transaction with the given object.
   *
   * @param obj - the object to compare with this one
   */
  @Override
  public boolean equals(Object obj) {
    /**
     * Returns true if this object is the same as this object.
     */
    if (this == obj) {
      return true;
    }
    /**
     * Returns true if the object is null or if the class is not the same as the object.
     */
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    /**
     * Returns true if this object is equal to the object.
     */
    if (!super.equals(obj)) {
      return false;
    }
    final Transaction other = (Transaction) obj;
    return Objects.equals(this.getId(), other.getId());
  }
}
