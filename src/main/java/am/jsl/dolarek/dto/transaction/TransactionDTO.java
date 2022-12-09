package am.jsl.dolarek.dto.transaction;

import java.io.Serializable;
import java.time.LocalDateTime;

import am.jsl.dolarek.domain.transaction.Transaction;
import am.jsl.dolarek.domain.transaction.TransactionSource;
import am.jsl.dolarek.domain.transaction.TransactionType;
import am.jsl.dolarek.domain.transaction.Transfer;
import am.jsl.dolarek.util.DateUtils;

/**
*Transakcja służy do tworzenia i aktualizowania transakcji z sieci.
*/
public class TransactionDTO implements Serializable {

    private long id;
    private String description;
    private long accountId;
    private long categoryId;
    private double amount;
    private byte status;
    private byte transactionType;
    private String transactionDate;
    private byte transactionSource = TransactionSource.MANUAL.getValue();
    private double exchangeRate;
    private long targetAccountId;
    private double convertedAmount;
    private long userId;

    public TransactionDTO() {
        super();
    }

    public TransactionDTO(long accountId, byte transactionType, LocalDateTime transactionDate, long userId) {
        super();
        this.accountId = accountId;
        this.transactionType = transactionType;
        this.transactionDate = DateUtils.format(transactionDate);
        this.userId = userId;
    }

    public boolean isTransfer() {
        return transactionType == TransactionType.TRANSFER.getValue();
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
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

    public String getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public LocalDateTime getTransactionLocalDateTime() {
        return DateUtils.parse(transactionDate);
    }

    public long getTargetAccountId() {
        return targetAccountId;
    }
    public void setTargetAccountId(long targetAccountId) {
        this.targetAccountId = targetAccountId;
    }

    public double getConvertedAmount() {
        return convertedAmount;
    }
    public void setConvertedAmount(double convertedAmount) {
        this.convertedAmount = convertedAmount;
    }

    public long getUserId() {
        return userId;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }
    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public byte getTransactionSource() {
        return transactionSource;
    }
    public void setTransactionSource(byte transactionSource) {
        this.transactionSource = transactionSource;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Transaction toTransaction() {
        Transaction transaction = new Transaction();
        transaction.setId(getId());
        transaction.setAccountId(getAccountId());
        transaction.setCategoryId(getCategoryId());
        transaction.setAmount(getAmount());
        transaction.setStatus(getStatus());
        transaction.setTransactionType(getTransactionType());
        transaction.setTransactionDate(getTransactionLocalDateTime());
        transaction.setDescription(getDescription());

        if (isTransfer()) {
            Transfer transfer = new Transfer();
            transfer.setTargetAccountId(getTargetAccountId());
            transfer.setConvertedAmount(getConvertedAmount());
            transfer.setRate(getExchangeRate());
            transaction.setTransfer(transfer);
        }

        return transaction;
    }

    public static TransactionDTO from(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(transaction.getId());
        transactionDTO.setAccountId(transaction.getAccountId());
        transactionDTO.setCategoryId(transaction.getCategoryId());
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setStatus(transaction.getStatus());
        transactionDTO.setTransactionType(transaction.getTransactionType());
        transactionDTO.setTransactionDate(DateUtils.format(transaction.getTransactionDate()));
        transactionDTO.setUserId(transaction.getUserId());
        transactionDTO.setDescription(transaction.getDescription());

        if (transactionDTO.isTransfer()) {
            Transfer transfer = transaction.getTransfer();
            transactionDTO.setTargetAccountId(transfer.getTargetAccountId());
            transactionDTO.setConvertedAmount(transfer.getConvertedAmount());
            transactionDTO.setExchangeRate(transfer.getRate());
        }

        return transactionDTO;
    }
}
