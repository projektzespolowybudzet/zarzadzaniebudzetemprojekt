package am.jsl.dolarek.dto.transaction;

import java.io.Serializable;

/**
*TransactionDetailsDTO służy do przesyłania szczegółowych informacji o transakcji.
*/
public class TransactionDetailsDTO extends BaseTransactionDTO implements Serializable {
    private String account;
    private String accountIcon;
    private String accountColor;
    private byte status;
    private long targetAccountId = 0;
    private String targetAccount;
    private String targetAccountIcon;
    private String targetAccountColor;
    private String targetAccountSymbol;
    private double exchangeRate;
    private double convertedAmount;

    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccountIcon() {
        return accountIcon;
    }
    public void setAccountIcon(String accountIcon) {
        this.accountIcon = accountIcon;
    }

    public String getAccountColor() {
        return accountColor;
    }
    public void setAccountColor(String accountColor) {
        this.accountColor = accountColor;
    }

    public byte getStatus() {
        return status;
    }
    public void setStatus(byte status) {
        this.status = status;
    }

    public long getTargetAccountId() {
        return targetAccountId;
    }
    public void setTargetAccountId(long targetAccountId) {
        this.targetAccountId = targetAccountId;
    }

    public String getTargetAccount() {
        return targetAccount;
    }
    public void setTargetAccount(String targetAccount) {
        this.targetAccount = targetAccount;
    }

    public String getTargetAccountIcon() {
        return targetAccountIcon;
    }
    public void setTargetAccountIcon(String targetAccountIcon) {
        this.targetAccountIcon = targetAccountIcon;
    }

    public String getTargetAccountColor() {
        return targetAccountColor;
    }
    public void setTargetAccountColor(String targetAccountColor) {
        this.targetAccountColor = targetAccountColor;
    }

    public String getTargetAccountSymbol() {
        return targetAccountSymbol;
    }
    public void setTargetAccountSymbol(String targetAccountSymbol) {
        this.targetAccountSymbol = targetAccountSymbol;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }
    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public double getConvertedAmount() {
        return convertedAmount;
    }
    public void setConvertedAmount(double convertedAmount) {
        this.convertedAmount = convertedAmount;
    }
}
