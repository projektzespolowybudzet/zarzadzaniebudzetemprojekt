package am.jsl.dolarek.dto.account;

import org.springframework.format.annotation.NumberFormat;

import am.jsl.dolarek.domain.account.Account;
import am.jsl.dolarek.dto.DescriptiveDTO;

import java.io.Serializable;

/**
*Służy do przesyłania danych konta między stronami internetowymi i kontrolerami.
*/
public class AccountDTO extends DescriptiveDTO implements Serializable {

    @NumberFormat(pattern = "#,##0.00")
    private double balance;
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
    
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Account toAccount() {
        Account account = new Account();
        account.setId(getId());
        account.setName(getName());
        account.setDescription(getDescription());
        account.setBalance(getBalance());
        account.setAccountType(getAccountType());
        account.setCurrency(getCurrency());
        account.setSymbol(getSymbol());
        account.setActive(isActive());
        account.setSortOrder(getSortOrder());
        account.setIcon(getIcon());
        account.setColor(getColor());
        return account;
    }

    public static AccountDTO from(Account account) {
        AccountDTO dto = new AccountDTO();
        dto.setId(account.getId());
        dto.setName(account.getName());
        dto.setDescription(account.getDescription());
        dto.setBalance(account.getBalance());
        dto.setAccountType(account.getAccountType());
        dto.setCurrency(account.getCurrency());
        dto.setSymbol(account.getSymbol());
        dto.setActive(account.isActive());
        dto.setSortOrder(account.getSortOrder());
        dto.setIcon(account.getIcon());
        dto.setColor(account.getColor());
        return dto;
    }
}
