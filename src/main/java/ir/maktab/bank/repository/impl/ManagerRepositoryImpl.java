package ir.maktab.bank.repository.impl;

import ir.maktab.bank.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.bank.domain.Manager;
import ir.maktab.bank.repository.ManagerRepository;

import javax.persistence.EntityManager;

public class ManagerRepositoryImpl extends BaseRepositoryImpl<Manager, Long> implements ManagerRepository {

    public ManagerRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Manager> getEntityClass() {
        return Manager.class;
    }
}
