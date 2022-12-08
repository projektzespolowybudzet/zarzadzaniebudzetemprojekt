package am.jsl.personalfinances.dto.account;

import org.springframework.format.annotation.NumberFormat;

import java.io.Serializable;

/**
*Używany do przenoszenia wyrównania salda konta z sieci.
*/
public class AdjustBalanceDTO implements Serializable {
/**
*Identyfikator konta
*/
    private long id;

/**
*Saldo do ustalenia
*/
    @NumberFormat(pattern = "#,##0.00")
    private double balance;

/**
*Identyfikator użytkownika konta
*/
    private long userId;

/**
*Getter dla właściwości id.
*@return Wartość dla właściwości id.
*/
    public long getId() {
        return id;
    }

/**
*Seter dla właściwości id.
*@param id Wartość do ustawienia dla właściwości id.
*/
    public void setId(long id) {
        this.id = id;
    }

/**
*Getter dla właściwości balance.
*@return Wartość dla właściwości balance.
*/
    public double getBalance() {
        return balance;
    }

/**
*Seter dla właściwości balance.
*@param balance Wartość do ustawienia dla właściwości balance.
*/
    public void setBalance(double balance) {
        this.balance = balance;
    }

/**
*Getter dla właściwości userId.
*@return Wartość dla właściwości userId.
*/
    public long getUserId() {
        return userId;
    }

/**
*Seter dla właściwości userId.
*@param userId Wartość do ustawienia dla właściwości userId.
*/
    public void setUserId(long userId) {
        this.userId = userId;
    }
}
