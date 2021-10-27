package ir.maktab.bank.domain;

import ir.maktab.bank.domain.base.User;
import ir.maktab.bank.domain.enumeration.UserType;

import javax.persistence.Entity;
import java.sql.Date;

@Entity
public class Admin extends User {
    public Admin() {
    }

    public Admin(String firstname, String lastname, String nationalCode,
                 Date birthday, String username, String password, UserType usertype) {
        super(firstname, lastname, nationalCode, birthday, username, password, usertype);
    }

}
