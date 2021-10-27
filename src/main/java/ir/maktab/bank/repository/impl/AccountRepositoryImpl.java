package ir.maktab.bank.repository.impl;

import ir.maktab.bank.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.bank.domain.Account;
import ir.maktab.bank.domain.Branch;
import ir.maktab.bank.domain.Employee;
import ir.maktab.bank.repository.AccountRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class AccountRepositoryImpl extends BaseRepositoryImpl<Account, Long> implements AccountRepository {

    public AccountRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Account> getEntityClass() {
        return Account.class;
    }

    @Override
    public List<Account> getAccountsByBranch(Branch branch) {
        return entityManager.createQuery("from Account a where a.branch = :branch" , Account.class)
                .setParameter("branch", branch)
                .getResultList();
    }
}
