package ir.maktab.bank.domain;

import ir.maktab.bank.domain.base.User;
import ir.maktab.bank.domain.enumeration.UserType;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = Customer.TABLE_NAME)
public class Customer extends User {

    public static final String TABLE_NAME = "customer";

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Set<Account> accounts = new HashSet<>();

    public Customer() {
    }

    public Customer(String firstname, String lastname, String nationalCode, Date birthday,
                    String username, String password, UserType usertype, Set<Account> accounts) {
        super(firstname, lastname, nationalCode, birthday, username, password, usertype);
        this.accounts = accounts;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}
