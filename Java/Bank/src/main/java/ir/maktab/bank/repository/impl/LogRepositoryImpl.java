package ir.maktab.bank.repository.impl;

import ir.maktab.bank.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.bank.domain.Log;
import ir.maktab.bank.repository.LogRepository;

import javax.persistence.EntityManager;

public class LogRepositoryImpl extends BaseRepositoryImpl<Log, Long> implements LogRepository {

    public LogRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Log> getEntityClass() {
        return Log.class;
    }
}
