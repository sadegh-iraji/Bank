package ir.maktab.bank.domain;

import ir.maktab.bank.base.domain.BaseEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = CreditCard.TABLE_NAME)
public class CreditCard extends BaseEntity<Long> {

    public static final String TABLE_NAME = "credit_card";

    private String cardNumber;

    private String password;

    private String secondPassword;

    private String cvv2;

    private Date expireDate;

    @OneToOne(mappedBy = "creditcard")
    private Account account;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "creditCard_id")
    private Set<Log> logs = new HashSet<>();

    public CreditCard() {
    }

    public CreditCard(String cardNumber, String password,
                      String cvv2, Date expireDate) {
        this.cardNumber = cardNumber;
        this.password = password;
        this.cvv2 = cvv2;
        this.expireDate = expireDate;
    }

    public CreditCard(String cardNumber, String password, String secondPassword,
                      String cvv2, Date expireDate, Account account, Set<Log> logs) {
        this.cardNumber = cardNumber;
        this.password = password;
        this.secondPassword = secondPassword;
        this.cvv2 = cvv2;
        this.expireDate = expireDate;
        this.account = account;
        this.logs = logs;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecondPassword() {
        return secondPassword;
    }

    public void setSecondPassword(String secondPassword) {
        this.secondPassword = secondPassword;
    }

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Set<Log> getLogs() {
        return logs;
    }

    public void setLogs(Set<Log> logs) {
        this.logs = logs;
    }
}
