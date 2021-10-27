package ir.maktab.bank.service;

import ir.maktab.bank.base.service.BaseService;
import ir.maktab.bank.domain.Branch;
import ir.maktab.bank.domain.Employee;

import java.util.List;

public interface EmployeeService extends BaseService<Employee, Long> {

    List<Employee> findAllByBranchId(Branch branch);

}
