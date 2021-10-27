package ir.maktab.bank.util;

import ir.maktab.bank.repository.*;
import ir.maktab.bank.repository.impl.*;
import ir.maktab.bank.service.*;
import ir.maktab.bank.service.impl.*;

import javax.persistence.EntityManager;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ApplicationContext {

    private ApplicationContext() {
    }

    private static final AccountRepository accountRepository;
    private static final AccountService accountService;

    static {
        EntityManager accountManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        accountRepository = new AccountRepositoryImpl(accountManager);
        accountService = new AccountServiceImpl(accountRepository);
    }

    private static final BranchRepository branchRepository;
    private static final BranchService branchService;

    static {
        EntityManager branchManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        branchRepository = new BranchRepositoryImpl(branchManager);
        branchService = new BranchServiceImpl(branchRepository);
    }

    private static final CreditCardRepository creditCardRepository;
    private static final CreditCardService creditCardService;

    static {
        EntityManager creditCardManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        creditCardRepository = new CreditCardRepositoryImpl(creditCardManager);
        creditCardService = new CreditCardServiceImpl(creditCardRepository);
    }

    private static final CustomerRepository customerRepository;
    private static final CustomerService customerService;

    static {
        EntityManager customerManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        customerRepository = new CustomerRepsitoryImpl(customerManager);
        customerService = new CustomerServiceImpl(customerRepository);
    }

    private static final EmployeeRepository employeeRepository;
    private static final EmployeeService employeeService;

    static {
        EntityManager employeeManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        employeeRepository = new EmployeeRepsitoryImpl(employeeManager);
        employeeService = new EmployeeServiceImpl(employeeRepository);
    }


    private static final LogRepository logRepository;
    private static final LogService logService;

    static {
        EntityManager logManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        logRepository = new LogRepositoryImpl(logManager);
        logService = new LogServiceImpl(logRepository);
    }

    private static final ManagerRepository managerRepository;
    private static final ManagerService managerService;

    static {
        EntityManager managerManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        managerRepository = new ManagerRepositoryImpl(managerManager);
        managerService = new ManagerServiceImpl(managerRepository);
    }

    private static final UserRepository userRepository;
    private static final UserService userService;

    static {
        EntityManager userManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        userRepository = new UserRepositoryImpl(userManager);
        userService = new UserServiceImpl(userRepository);
    }

    private static final Scanner textScanner = new Scanner(System.in);

    private static final Scanner numericScanner = new Scanner(System.in);

    public static Scanner getTextScanner() {
        return textScanner;
    }

    public static Scanner getNumericScanner() {
        return numericScanner;
    }

    public static UserRepository getUserRepository() {
        return userRepository;
    }

    public static UserService getUserService() {
        return userService;
    }

    public static AccountRepository getAccountRepository() {
        return accountRepository;
    }

    public static AccountService getAccountService() {
        return accountService;
    }

    public static BranchRepository getBranchRepository() {
        return branchRepository;
    }

    public static BranchService getBranchService() {
        return branchService;
    }

    public static CreditCardRepository getCreditCardRepository() {
        return creditCardRepository;
    }

    public static CreditCardService getCreditCardService() {
        return creditCardService;
    }

    public static CustomerRepository getCustomerRepository() {
        return customerRepository;
    }

    public static CustomerService getCustomerService() {
        return customerService;
    }

    public static EmployeeRepository getEmployeeRepository() {
        return employeeRepository;
    }

    public static EmployeeService getEmployeeService() {
        return employeeService;
    }

    public static LogRepository getLogRepository() {
        return logRepository;
    }

    public static LogService getLogService() {
        return logService;
    }

    public static ManagerRepository getManagerRepository() {
        return managerRepository;
    }

    public static ManagerService getManagerService() {
        return managerService;
    }

    public static int intFromScannerReturn(int number) {
        do {
            try {
                number = getNumericScanner().nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Please Enter an Integer Number !");
                getNumericScanner().next();
            }
        } while (number < 1);
        return number;
    }

    public static long longFromScannerReturn(long number) {
        do {
            try {
                number = ApplicationContext.getNumericScanner().nextLong();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Please Enter an Long Number !");
                ApplicationContext.getNumericScanner().next();
            }
        } while (number < 1);
        return number;
    }

}
