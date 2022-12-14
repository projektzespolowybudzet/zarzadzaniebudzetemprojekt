package am.jsl.dolarek.domain.transaction;

import java.time.LocalDateTime;
import java.util.Objects;

import am.jsl.dolarek.domain.Descriptive;

/**
*Obiekt domeny transakcji.
*/
public class Transaction extends Descriptive {
    private long accountId;
    private long categoryId;
    private double amount;
    /**
     * @see TransactionStatus
     */
    private byte status;
    private byte transactionType;
    /**
     * @see TransactionSource
     */
    private byte transactionSource = TransactionSource.MANUAL.getValue();
    private LocalDateTime transactionDate;
    /**
     * @see Transfer
     */
    private Transfer transfer = null;
    /**
    *@return true w przypadku gdy przelew
    */
    public boolean isTransferType() {
        return transactionType == TransactionType.TRANSFER.getValue();
    }

    public Transfer getTransfer() {
        return transfer;
    }
    public void setTransfer(Transfer transfer) {
        this.transfer = transfer;
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

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public byte getStatus() {
        return status;
    }
    public void setStatus(byte status) {
        this.status = status;
    }

    public byte getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(byte transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public byte getTransactionSource() {
        return transactionSource;
    }
    public void setTransactionSource(byte transactionSource) {
        this.transactionSource = transactionSource;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        final Transaction other = (Transaction) obj;
        return Objects.equals(this.getId(), other.getId());
    }
}
