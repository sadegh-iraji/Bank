package ir.maktab.bank.service.impl;

import ir.maktab.bank.base.service.impl.BaseServiceImpl;
import ir.maktab.bank.domain.Account;
import ir.maktab.bank.domain.Branch;
import ir.maktab.bank.repository.AccountRepository;
import ir.maktab.bank.repository.BranchRepository;
import ir.maktab.bank.service.AccountService;
import ir.maktab.bank.service.BranchService;

public class BranchServiceImpl extends BaseServiceImpl<Branch, Long, BranchRepository> implements
        BranchService {

    public BranchServiceImpl(BranchRepository repository) {
        super(repository);
    }
}
