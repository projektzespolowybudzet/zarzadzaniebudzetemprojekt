package am.jsl.dolarek.domain.account;


import java.io.Serializable;
import java.util.Objects;

import am.jsl.dolarek.domain.Descriptive;

/**
*Obiekt domeny konta.
*/
public class Account extends Descriptive implements Serializable {
    private double balance;
    /**
     * @see AccountType
     */
    private byte accountType;
    private String currency;
    private String symbol;
    private boolean active = true;
    private String icon;
    private String color;
    private int sortOrder;

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public byte getAccountType() {
        return accountType;
    }
    public void setAccountType(byte accountType) {
        this.accountType = accountType;
    }

    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public int getSortOrder() {
        return sortOrder;
    }
    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;

        if (o instanceof Account) {
            final Account other = (Account) o;
            return Objects.equals(getId(), other.getId())
                    && Objects.equals(getName(), other.getName());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
