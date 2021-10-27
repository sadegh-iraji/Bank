package ir.maktab.bank.domain;

import ir.maktab.bank.base.domain.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = Account.TABLE_NAME)
public class Account extends BaseEntity<Long> {

    public static final String TABLE_NAME = "account";

    @Column(unique = true)
    private String accountNumber;

    private Double credit;

    private boolean isActive;

    @OneToOne(cascade = CascadeType.ALL)
    private CreditCard creditcard;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Branch branch;

    public Account() {
    }

    public Account(String accountNumber, Double credit, boolean isActive,
                   CreditCard creditcard, Customer customer, Branch branch) {
        this.accountNumber = accountNumber;
        this.credit = credit;
        this.isActive = isActive;
        this.creditcard = creditcard;
        this.customer = customer;
        this.branch = branch;
    }

    public Account(String accountNumber, Double credit,
                   boolean isActive, CreditCard creditcard, Branch branch) {
        this.accountNumber = accountNumber;
        this.credit = credit;
        this.isActive = isActive;
        this.creditcard = creditcard;
        this.branch = branch;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public CreditCard getCreditcard() {
        return creditcard;
    }

    public void setCreditcard(CreditCard creditcard) {
        this.creditcard = creditcard;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public boolean getActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
