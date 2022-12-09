package am.jsl.personalfinances.dto.transaction;

import am.jsl.personalfinances.domain.transaction.TransactionType;

import java.io.Serializable;

/**
*TransactionListTotalDTO służy do wyświetlania sum transakcji pogrupowanych według typu transakcji i rachunku.
*/
public class TransactionListTotalDTO implements Serializable {

    private byte transactionType;
    private double total;
    private String symbol;

    public String getTransactionTypeClass() {
        return "trType" + transactionType;
    }

    public boolean isExpense() {
        return transactionType == TransactionType.EXPENSE.getValue();
    }

    public byte getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(byte transactionType) {
        this.transactionType = transactionType;
    }

    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }

    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
