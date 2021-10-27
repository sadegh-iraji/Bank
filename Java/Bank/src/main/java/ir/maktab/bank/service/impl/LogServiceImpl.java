package ir.maktab.bank.service.impl;

import ir.maktab.bank.base.service.impl.BaseServiceImpl;
import ir.maktab.bank.domain.Log;
import ir.maktab.bank.repository.LogRepository;
import ir.maktab.bank.service.LogService;

public class LogServiceImpl extends BaseServiceImpl<Log, Long, LogRepository>
        implements LogService {

    public LogServiceImpl(LogRepository repository) {
        super(repository);
    }
}
