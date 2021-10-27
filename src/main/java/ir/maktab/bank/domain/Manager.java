package ir.maktab.bank.domain;

import ir.maktab.bank.domain.base.User;
import ir.maktab.bank.domain.enumeration.UserType;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = Manager.TABLE_NAME)
public class Manager extends User {

    public static final String TABLE_NAME = "manager";

    @OneToOne(cascade = CascadeType.ALL)
    private Branch branch;

    public Manager() {
    }

    public Manager(String firstname, String lastname, String nationalCode,
                   Date birthday, String username, String password, UserType usertype, Branch branch) {
        super(firstname, lastname, nationalCode, birthday, username, password, usertype);
        this.branch = branch;
    }

    public Manager(String firstname, String lastname,
                   String nationalCode, Date birthday, String username, String password, UserType usertype) {
        super(firstname, lastname, nationalCode, birthday, username, password, usertype);
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
