package ir.maktab.bank.repository;

import ir.maktab.bank.base.repository.BaseRepository;
import ir.maktab.bank.domain.Account;
import ir.maktab.bank.domain.Branch;

import java.util.List;

public interface AccountRepository extends BaseRepository<Account, Long> {

    List<Account> getAccountsByBranch(Branch branch);

}
