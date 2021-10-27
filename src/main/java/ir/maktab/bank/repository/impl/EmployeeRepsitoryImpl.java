package ir.maktab.bank.repository.impl;

import ir.maktab.bank.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.bank.domain.Branch;
import ir.maktab.bank.domain.Employee;
import ir.maktab.bank.repository.EmployeeRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class EmployeeRepsitoryImpl extends BaseRepositoryImpl<Employee, Long> implements EmployeeRepository {

    public EmployeeRepsitoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Employee> getEntityClass() {
        return Employee.class;
    }

    @Override
    public List<Employee> findAllByBranchId(Branch branch) {
        return entityManager.createQuery("from Employee e where e.branch = :branch" , Employee.class)
                .setParameter("branch", branch)
                .getResultList();
    }
}
