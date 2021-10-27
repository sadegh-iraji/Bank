package ir.maktab.bank.domain;

import ir.maktab.bank.domain.base.User;
import ir.maktab.bank.domain.enumeration.UserType;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = Employee.TABLE_NAME)
public class Employee extends User {

    public static final String TABLE_NAME = "employee";

    @ManyToOne
    private Branch branch;

    public Employee() {
    }

    public Employee(String firstname, String lastname, String nationalCode, Date birthday, String username, String password, UserType usertype, Branch branch) {
        super(firstname, lastname, nationalCode, birthday, username, password, usertype);
        this.branch = branch;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
