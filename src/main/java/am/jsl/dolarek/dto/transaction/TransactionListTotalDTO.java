package am.jsl.dolarek.dto.transaction;

import am.jsl.dolarek.domain.transaction.TransactionType;
import java.io.Serializable;

/**
 *TransactionListTotalDTO służy do wyświetlania sum transakcji pogrupowanych według typu transakcji i rachunku.
 */
public class TransactionListTotalDTO implements Serializable {

  /**
   * Typ transakcji
   */
  private byte transactionType;

  /**
   * Całkowita kwota
   */
  private double total;

  /**
   * Natywny symbol waluty
   */
  private String symbol;

  /**
   * Zwraca klasę CSS dla typu transakcji
   *
   * @return klasa CSS
   */
  public String getTransactionTypeClass() {
    return "trType" + transactionType;
  }

  /**
   * Zwraca prawdę, jeśli typ transakcji jest kosztem.
   *
   * @return prawda, jeśli typ transakcji jest kosztem.
   */
  public boolean isExpense() {
    return transactionType == TransactionType.EXPENSE.getValue();
  }

  /**
   * Getter for property 'transactionType'.
   *
   * @return Value for property 'transactionType'.
   */
  public byte getTransactionType() {
    return transactionType;
  }

  /**
   * Setter for property 'transactionType'.
   *
   * @param transactionType Value to set for property 'transactionType'.
   */
  public void setTransactionType(byte transactionType) {
    this.transactionType = transactionType;
  }

  /**
   * Getter for property 'total'.
   *
   * @return Value for property 'total'.
   */
  public double getTotal() {
    return total;
  }

  /**
   * Setter for property 'total'.
   *
   * @param total Value to set for property 'total'.
   */
  public void setTotal(double total) {
    this.total = total;
  }

  /**
   * Getter for property 'symbol'.
   *
   * @return Value for property 'symbol'.
   */
  public String getSymbol() {
    return symbol;
  }

  /**
   * Setter for property 'symbol'.
   *
   * @param symbol Value to set for property 'symbol'.
   */
  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }
}
