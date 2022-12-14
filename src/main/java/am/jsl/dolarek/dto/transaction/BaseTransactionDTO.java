package am.jsl.dolarek.dto.transaction;

import java.util.Date;

import am.jsl.dolarek.domain.transaction.TransactionType;
import am.jsl.dolarek.dto.DescriptiveDTO;

/**
*BaseTransactionDTO zawiera szczegóły transakcji dotyczące przesyłania między warstwami aplikacji.
*/
public class BaseTransactionDTO extends DescriptiveDTO {
 
    protected String symbol;
    protected String category;
    protected String categoryIcon;
    protected String categoryColor;
    protected String parentCategory;
    protected String parentCategoryIcon;
    protected String parentCategoryColor;
    protected double amount;
    protected byte transactionType;
    protected Date transactionDate;

    public boolean isExpense() {
        return transactionType == TransactionType.EXPENSE.getValue();
    }
    
    public boolean isIncome() {
        return transactionType == TransactionType.INCOME.getValue();
    }

    public boolean isTransfer() {
        return transactionType == TransactionType.TRANSFER.getValue();
    }
    
    public String getTransactionTypeClass() {
        return "trType" + transactionType;
    }
    
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getCategoryIcon() {
        return categoryIcon;
    }
    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }
    
    public String getCategoryColor() {
        return categoryColor;
    }
    public void setCategoryColor(String categoryColor) {
        this.categoryColor = categoryColor;
    }
    
    public String getParentCategory() {
        return parentCategory;
    }
    public void setParentCategory(String parentCategory) {
        this.parentCategory = parentCategory;
    }
    
    public String getParentCategoryIcon() {
        return parentCategoryIcon;
    }
    public void setParentCategoryIcon(String parentCategoryIcon) {
        this.parentCategoryIcon = parentCategoryIcon;
    }
    
    public String getParentCategoryColor() {
        return parentCategoryColor;
    }
    public void setParentCategoryColor(String parentCategoryColor) {
        this.parentCategoryColor = parentCategoryColor;
    }
    
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public byte getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(byte transactionType) {
        this.transactionType = transactionType;
    }
    
    public Date getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
