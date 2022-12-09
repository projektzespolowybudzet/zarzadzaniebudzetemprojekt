package am.jsl.dolarek.search.transaction;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import am.jsl.dolarek.dto.transaction.TransactionListDTO;
import am.jsl.dolarek.search.Query;

/**
*Niestandardowe {@link Query} do wyszukiwania elementów {@link TransactionListDTO}.
*/
public class TransactionSearchQuery extends Query<TransactionListDTO> implements Serializable {
    private long accountId;
    private long categoryId;
    private short transactionType;
    private short transactionSource;
    private Date startDate;
    private Date endDate;
    private long userId;
    private String description;

    private boolean calculateTotals = true;

/**
*Tworzy instancję nowego zapytania wyszukiwania transakcji.
*
*@param page strona
*@param pageSize rozmiar strony
*/
    public TransactionSearchQuery(int page, int pageSize) {
        super(page, pageSize);
    }


    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public short getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(short transactionType) {
        this.transactionType = transactionType;
    }

    public short getTransactionSource() {
        return transactionSource;
    }

    public void setTransactionSource(short transactionSource) {
        this.transactionSource = transactionSource;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCalculateTotals() {
        return calculateTotals;
    }

    public void setCalculateTotals(boolean calculateTotals) {
        this.calculateTotals = calculateTotals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, categoryId, transactionType, transactionSource, startDate, endDate, userId, description);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final TransactionSearchQuery other = (TransactionSearchQuery) obj;
        return Objects.equals(this.accountId, other.accountId)
                && Objects.equals(this.categoryId, other.categoryId)
                && Objects.equals(this.transactionType, other.transactionType)
                && Objects.equals(this.transactionSource, other.transactionSource)
                && Objects.equals(this.startDate, other.startDate)
                && Objects.equals(this.endDate, other.endDate)
                && Objects.equals(this.userId, other.userId)
                && Objects.equals(this.description, other.description);
    }
}
