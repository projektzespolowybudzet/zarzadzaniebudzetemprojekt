package am.jsl.personalfinances.dto.transaction;

import java.io.Serializable;

/**
*Zawiera transakcje pogrupowane według kategorii i konta.
*/
public class TransactionByCategoryDTO implements Serializable {
    private String category;
    private String categoryIcon;
    private String categoryColor;
    private String parentCategory;
    private String parentCategoryIcon;
    private String parentCategoryColor;
    private double total;
    private String totalStr;
    private String percent;
    private String symbol;
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

    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }

    public String getPercent() {
        return percent;
    }
    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTotalStr() {
        return totalStr;
    }
    public void setTotalStr(String totalStr) {
        this.totalStr = totalStr;
    }
}
