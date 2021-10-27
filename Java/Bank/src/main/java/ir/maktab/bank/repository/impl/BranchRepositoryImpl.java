package ir.maktab.bank.repository.impl;

import ir.maktab.bank.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.bank.domain.Branch;
import ir.maktab.bank.repository.BranchRepository;

import javax.persistence.EntityManager;

public class BranchRepositoryImpl extends BaseRepositoryImpl<Branch, Long> implements BranchRepository {

    public BranchRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Branch> getEntityClass() {
        return Branch.class;
    }
}
