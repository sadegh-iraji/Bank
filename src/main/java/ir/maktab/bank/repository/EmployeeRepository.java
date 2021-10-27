package ir.maktab.bank.repository;

import ir.maktab.bank.base.repository.BaseRepository;
import ir.maktab.bank.domain.Branch;
import ir.maktab.bank.domain.Employee;

import java.util.List;

public interface EmployeeRepository extends BaseRepository<Employee, Long> {

    List<Employee> findAllByBranchId(Branch branch);
}
