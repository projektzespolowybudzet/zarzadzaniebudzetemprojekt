package am.jsl.dolarek.dto.account;

import am.jsl.dolarek.dto.DescriptiveDTO;

/**
*Służy do przesyłania i wyświetlania szczegółów konta na stronach z listą kont.
*/
public class AccountListDTO extends DescriptiveDTO {

    private double balance;
    private byte accountType;
    private String currency;
    private String symbol;
    private boolean active;
    private String icon;
    private String color;

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
}
