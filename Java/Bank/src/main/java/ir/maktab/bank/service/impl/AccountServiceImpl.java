package ir.maktab.bank.service.impl;

import ir.maktab.bank.base.service.impl.BaseServiceImpl;
import ir.maktab.bank.domain.Account;
import ir.maktab.bank.domain.Branch;
import ir.maktab.bank.repository.AccountRepository;
import ir.maktab.bank.service.AccountService;

import java.util.List;

public class AccountServiceImpl extends BaseServiceImpl<Account, Long, AccountRepository> implements
        AccountService {

    public AccountServiceImpl(AccountRepository repository) {
        super(repository);
    }

    @Override
    public List<Account> getAccountsByBranch(Branch branch) {
        return repository.getAccountsByBranch(branch);
    }
}
