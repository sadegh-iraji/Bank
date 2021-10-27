package ir.maktab.bank.domain.base;

import ir.maktab.bank.base.domain.BaseEntity;
import ir.maktab.bank.domain.enumeration.UserType;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = User.TABLE_NAME)
@Inheritance
public class User extends BaseEntity<Long> {

    public static final String TABLE_NAME = "user_table";

    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String NATIONAL_CODE = "national_code";
    public static final String BIRTHDAY = "birthday";
    public static final String USER_TYPE = "user_type";
    public static final String USER_NAME = "user_name";
    public static final String PASSWORD = "password";


    @Column(name = FIRST_NAME)
    private String firstname;

    @Column(name = LAST_NAME)
    private String lastname;

    @Column(name = NATIONAL_CODE, unique = true)
    private String nationalCode;

    @Column(name = BIRTHDAY)
    private Date birthday;

    @Column(name = USER_NAME, unique = true)
    private String username;

    @Column(name = PASSWORD)
    private String password;

    @Column(name = USER_TYPE)
    @Enumerated(EnumType.STRING)
    private UserType usertype;

    public User() {
    }

    public User(String firstname, String lastname,
                String nationalCode, Date birthday, String username, String password, UserType usertype) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.nationalCode = nationalCode;
        this.birthday = birthday;
        this.username = username;
        this.password = password;
        this.usertype = usertype;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public UserType getUsertype() {
        return usertype;
    }

    public void setUsertype(UserType usertype) {
        this.usertype = usertype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
