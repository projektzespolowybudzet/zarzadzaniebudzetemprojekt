package am.jsl.dolarek.dto.account;

import org.springframework.format.annotation.NumberFormat;

import java.io.Serializable;

/**
*Używany do przenoszenia wyrównania salda konta z sieci.
*/
public class AdjustBalanceDTO implements Serializable {

    private long id;
    @NumberFormat(pattern = "#,##0.00")
    private double balance;
    private long userId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
}
