package ir.maktab.bank.service.impl;

import ir.maktab.bank.base.service.impl.BaseServiceImpl;
import ir.maktab.bank.domain.Manager;
import ir.maktab.bank.repository.ManagerRepository;
import ir.maktab.bank.service.ManagerService;

public class ManagerServiceImpl extends BaseServiceImpl<Manager, Long, ManagerRepository>
        implements ManagerService {
    public ManagerServiceImpl(ManagerRepository repository) {
        super(repository);
    }
}
