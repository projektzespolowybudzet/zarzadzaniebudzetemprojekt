package am.jsl.personalfinances.dto.transaction;

import am.jsl.personalfinances.domain.transaction.Transaction;
import am.jsl.personalfinances.util.DateUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
*AddTransactionsDTO jest używany do transakcji wsadowych ze strony dodawania transakcji wsadowych.
*/
public class AddTransactionsDTO implements Serializable {

    private String description;
    private long accountId;
    private byte transactionType;
    private String transactionDate;
    private long userId;
    private List<Transaction> transactions = new ArrayList<>();

    public AddTransactionsDTO() {
        super();
    }

    public AddTransactionsDTO(long accountId, byte transactionType, LocalDateTime transactionDate, long userId) {
        super();
        this.accountId = accountId;
        this.transactionType = transactionType;
        this.transactionDate = DateUtils.format(transactionDate);
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public long getAccountId() {
        return accountId;
    }
    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public byte getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(byte transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
    
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
    public List<Transaction> getTransactions() {
        return transactions;
    }
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
