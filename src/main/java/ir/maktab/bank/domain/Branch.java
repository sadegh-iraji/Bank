package ir.maktab.bank.domain;

import ir.maktab.bank.base.domain.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = Branch.TABLE_NAME)
public class Branch extends BaseEntity<Long> {

    public static final String TABLE_NAME = "branch";

    private String branchCode;

    private String branchName;

    @OneToMany
    @JoinColumn(name = "branch_id")
    private Set<Account> accounts = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "branch_id")
    private Set<Employee> employees = new HashSet<>();

    @OneToOne(mappedBy = "branch", cascade = CascadeType.PERSIST)
    private Manager manager;

    public Branch() {
    }

    public Branch(String branchCode, String branchName, Set<Account> accounts,
                  Set<Employee> employees, Manager manager) {
        this.branchCode = branchCode;
        this.branchName = branchName;
        this.accounts = accounts;
        this.employees = employees;
        this.manager = manager;
    }

    public Branch(String branchCode, String branchName, Manager manager) {
        this.branchCode = branchCode;
        this.branchName = branchName;
        this.manager = manager;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
