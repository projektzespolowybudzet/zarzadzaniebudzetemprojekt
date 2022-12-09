package am.jsl.dolarek.search.transaction;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import am.jsl.dolarek.dto.transaction.TransactionByCategoryDTO;
import am.jsl.dolarek.search.Query;

/**
*Niestandardowe {@link Query} do wyszukiwania elementów {@link TransactionByCategoryDTO}.
*/
public class TransactionByCategorySearchQuery extends Query<TransactionByCategoryDTO> implements Serializable {
    private long accountId;
    private short transactionType;
    private Date startDate;
    private Date endDate;
    private long userId;

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public short getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(short transactionType) {
        this.transactionType = transactionType;
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

    @Override
    public int hashCode() {
        return Objects.hash(accountId, transactionType, startDate, endDate, userId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final TransactionByCategorySearchQuery other = (TransactionByCategorySearchQuery) obj;
        return Objects.equals(this.accountId, other.accountId)
                && Objects.equals(this.transactionType, other.transactionType)
                && Objects.equals(this.startDate, other.startDate)
                && Objects.equals(this.endDate, other.endDate)
                && Objects.equals(this.userId, other.userId);
    }
}
