package am.jsl.dolarek.dto.transaction;

import am.jsl.dolarek.domain.transaction.TransactionType;
import am.jsl.dolarek.dto.DescriptiveDTO;
import java.util.Date;

/**
 *BaseTransactionDTO zawiera szczegóły transakcji dotyczące przesyłania między warstwami aplikacji.
 */
public class BaseTransactionDTO extends DescriptiveDTO {

  /**
   * Native Waluty Symbol konta
   */
  protected String symbol;

  /**
   * Nazwa kategorii tej transakcji
   */
  protected String category;

  /**
   * Ikona kategorii tej transakcji
   */
  protected String categoryIcon;

  /**
   * Kolor kategorii tej transakcji
   */
  protected String categoryColor;

  /**
   * Nazwa kategorii nadrzędnej
   */
  protected String parentCategory;

  /**
   * Ikona kategorii rodziców
   */
  protected String parentCategoryIcon;

  /**
   * Kolor kategorii nadrzędnej
   */
  protected String parentCategoryColor;

  /**
   * Ilość tej transakcji
   */
  protected double amount;

  /**
   * Rodzaj tej transakcji
   */
  protected byte transactionType;

  /**
   * Data tej transakcji
   */
  protected Date transactionDate;

  /**
   * Zwraca prawdziwie, jeśli wydatek
   *
   * @return Prawda, jeśli wydatek
   */
  public boolean isExpense() {
    return transactionType == TransactionType.EXPENSE.getValue();
  }

  /**
   * Zwraca prawde, jeśli wpływ
   *
   * @return Prawda, jeśli wpływ
   */
  public boolean isIncome() {
    return transactionType == TransactionType.INCOME.getValue();
  }

  /**
   * Zwraca prawde, jeśli transfer.
   *
   * @return Prawda, jeśli transfer
   */
  public boolean isTransfer() {
    return transactionType == TransactionType.TRANSFER.getValue();
  }

  /**
   * Zwraca typ tranzakcji
   *
   * @return prawda, jeśli typ transakcji to transfer
   */
  public String getTransactionTypeClass() {
    return "trType" + transactionType;
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

  /**
   * Getter for property 'category'.
   *
   * @return Value for property 'category'.
   */
  public String getCategory() {
    return category;
  }

  /**
   * Setter for property 'category'.
   *
   * @param category Value to set for property 'category'.
   */
  public void setCategory(String category) {
    this.category = category;
  }

  /**
   * Getter for property 'categoryIcon'.
   *
   * @return Value for property 'categoryIcon'.
   */
  public String getCategoryIcon() {
    return categoryIcon;
  }

  /**
   * Setter for property 'categoryIcon'.
   *
   * @param categoryIcon Value to set for property 'categoryIcon'.
   */
  public void setCategoryIcon(String categoryIcon) {
    this.categoryIcon = categoryIcon;
  }

  /**
   * Getter for property 'categoryColor'.
   *
   * @return Value for property 'categoryColor'.
   */
  public String getCategoryColor() {
    return categoryColor;
  }

  /**
   * Setter for property 'categoryColor'.
   *
   * @param categoryColor Value to set for property 'categoryColor'.
   */
  public void setCategoryColor(String categoryColor) {
    this.categoryColor = categoryColor;
  }

  /**
   * Getter for property 'parentCategory'.
   *
   * @return Value for property 'parentCategory'.
   */
  public String getParentCategory() {
    return parentCategory;
  }

  /**
   * Setter for property 'parentCategory'.
   *
   * @param parentCategory Value to set for property 'parentCategory'.
   */
  public void setParentCategory(String parentCategory) {
    this.parentCategory = parentCategory;
  }

  /**
   * Getter for property 'parentCategoryIcon'.
   *
   * @return Value for property 'parentCategoryIcon'.
   */
  public String getParentCategoryIcon() {
    return parentCategoryIcon;
  }

  /**
   * Setter for property 'parentCategoryIcon'.
   *
   * @param parentCategoryIcon Value to set for property 'parentCategoryIcon'.
   */
  public void setParentCategoryIcon(String parentCategoryIcon) {
    this.parentCategoryIcon = parentCategoryIcon;
  }

  /**
   * Getter for property 'parentCategoryColor'.
   *
   * @return Value for property 'parentCategoryColor'.
   */
  public String getParentCategoryColor() {
    return parentCategoryColor;
  }

  /**
   * Setter for property 'parentCategoryColor'.
   *
   * @param parentCategoryColor Value to set for property 'parentCategoryColor'.
   */
  public void setParentCategoryColor(String parentCategoryColor) {
    this.parentCategoryColor = parentCategoryColor;
  }

  /**
   * Getter for property 'amount'.
   *
   * @return Value for property 'amount'.
   */
  public double getAmount() {
    return amount;
  }

  /**
   * Setter for property 'amount'.
   *
   * @param amount Value to set for property 'amount'.
   */
  public void setAmount(double amount) {
    this.amount = amount;
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
   * Getter for property 'transactionDate'.
   *
   * @return Value for property 'transactionDate'.
   */
  public Date getTransactionDate() {
    return transactionDate;
  }

  /**
   * Setter for property 'transactionDate'.
   *
   * @param transactionDate Value to set for property 'transactionDate'.
   */
  public void setTransactionDate(Date transactionDate) {
    this.transactionDate = transactionDate;
  }
}
