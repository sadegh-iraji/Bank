package ir.maktab.bank.util;

import ir.maktab.bank.domain.*;
import ir.maktab.bank.domain.base.User;
import ir.maktab.bank.domain.enumeration.UserType;
import ir.maktab.bank.view.DetailsView;
import ir.maktab.bank.view.MenuView;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Operations {

    static Account customerAccount;
    static String destinationCardNumber;
    static double transferCredit;

    public static void mainAppStart() {
        System.out.println("*** Maktab Bank ***");
        System.out.println();
        login();
        System.out.println("🌸🌸 Welcome Dear "
                + SecurityContext.getActiveUser().getFirstname()
                + " " + SecurityContext.getActiveUser().getLastname());
        mainMenu();
    }

    private static void login() {
        String username;
        String password;

        while (true) {
            System.out.println("Username: ");
            username = ApplicationContext.getTextScanner().next();
            System.out.println("Password:");
            password = ApplicationContext.getTextScanner().next();
            if (ApplicationContext.getUserService().existByUsername(username)) {
                User user = ApplicationContext.getUserService().getUserByUsername(username);
                if (user.getPassword().equals(password)) {
                    SecurityContext.setActiveUser(user);
                    break;
                } else {
                    System.out.println("----> Wrong Password <----");
                }
            } else {
                System.out.println("----> No Match Found <----");
            }
        }

    }

    private static void mainMenu() {
        UserType userType = SecurityContext.getActiveUser().getUsertype();
        switch (userType) {
            case ADMIN:
                adminMenu();
                break;
            case MANAGER:
                Manager activeManager =
                        ApplicationContext.getManagerService().findById(SecurityContext.getActiveUser().getId());
                managerMenu(activeManager);
                break;
            case EMPLOYEE:
                Employee activeEmployee =
                        ApplicationContext.getEmployeeService().findById(SecurityContext.getActiveUser().getId());
                employeeMenu(activeEmployee);
                break;
            case CUSTOMER:
                Customer activeCustomer =
                        ApplicationContext.getCustomerService().findById(SecurityContext.getActiveUser().getId());
                customerMenu(activeCustomer);
                break;
        }
    }

    private static void adminMenu() {
        boolean adminMenuRepeat = true;
        while (adminMenuRepeat) {
            MenuView.adminMenuView();
            int adminMenuSwitch = 0;
            adminMenuSwitch = ApplicationContext.intFromScannerReturn(adminMenuSwitch);

            switch (adminMenuSwitch) {
                case 1:
                    addBranch();
                    break;
                case 2:
                    removeBranch();
                    break;
                default:
                    adminMenuRepeat = false;
            }
        }
    }

    private static void managerMenu(Manager activeManager) {
        boolean managerMenuRepeat = true;
        while (managerMenuRepeat) {
            MenuView.managerMenuView();
            int managerSwitch = 0;
            managerSwitch = ApplicationContext.intFromScannerReturn(managerSwitch);

            switch (managerSwitch) {
                case 1:
                    addEmployee(activeManager);
                    break;
                case 2:
                    removeEmployee(activeManager);
                    break;
                case 3:
                    viewBranchEmployees(activeManager);
                    break;
                case 4:
                    viewBranchCustomers(activeManager);
                    break;
                default:
                    managerMenuRepeat = false;
            }
        }
    }

    private static void employeeMenu(Employee activeEmployee) {
        boolean employeeMenuRepeat = true;
        while (employeeMenuRepeat) {
            MenuView.employeeMenuView();
            int employeeSwitch = 0;
            employeeSwitch = ApplicationContext.intFromScannerReturn(employeeSwitch);

            switch (employeeSwitch) {
                case 1:
                    addCustomer(activeEmployee);
                    break;
                case 2:
                    viewListOfCustomerAccounts(activeEmployee);
                    break;
                default:
                    employeeMenuRepeat = false;
            }
        }
    }

    private static void customerMenu(Customer activeCustomer) {
        boolean customerMenuRepeat = true;
        while (customerMenuRepeat) {
            MenuView.customerMenuView();
            int customerSwitch = 0;
            customerSwitch = ApplicationContext.intFromScannerReturn(customerSwitch);

            switch (customerSwitch) {
                case 1:
                    activeCustomer = changeProfilePassword(activeCustomer);
                    break;
                case 2:
                    setOrChangeSecondPassword(activeCustomer);
                    break;
                case 3:
                    transferMoney(activeCustomer);
                    break;
                default:
                    customerMenuRepeat = false;
            }
        }
    }

    private static void addBranch() {
        String branchCode;
        String branchName;
        Manager branchManager;

        do {
            System.out.println("Enter The Branch Code (4 Digits): ");
            branchCode = ApplicationContext.getTextScanner().next();
        } while (!Authentication.branchCodeAuthenticate(branchCode));

        System.out.println("Branch Name :");
        ApplicationContext.getTextScanner().nextLine();
        branchName = ApplicationContext.getTextScanner().nextLine();

        System.out.println("Now Please Introduce The Manager of The Branch\n" +
                "===============================================================");
        branchManager = addBranchManager();

        ApplicationContext.getBranchService().save(new Branch(branchCode, branchName, branchManager));
    }

    private static Manager addBranchManager() {
        String firstname;
        String lastname;
        String nationalCode;
        Date birthday;
        String username;
        String password;
        UserType userType;
        String bday;

        System.out.println("Enter Manager's First Name :");
        firstname = ApplicationContext.getTextScanner().next();

        System.out.println("Enter Manager's Last Name :");
        lastname = ApplicationContext.getTextScanner().next();

        do {
            System.out.println("Enter Manager's National Code (10 Digits) :");
            nationalCode = ApplicationContext.getTextScanner().next();
        } while (!Authentication.nationalCodeAuthenticate(nationalCode));

        do {
            System.out.println("Enter Manager's Birthday (yyyy-mm-dd) :");
            bday = ApplicationContext.getTextScanner().next();
        } while (!Authentication.birthdayAuthenticate(bday));
        birthday = Date.valueOf(bday);

        System.out.println("Enter Manager's Username :");
        username = ApplicationContext.getTextScanner().next();

        password = nationalCode;

        System.out.println(" *** National Code is Set as Initial Password ***");

        userType = UserType.MANAGER;

        return new Manager(firstname, lastname, nationalCode, birthday, username, password, userType);

    }

    private static void removeBranch() {
        long id = 0L;
        List<Branch> allBranches = ApplicationContext.getBranchService().findAll();
        DetailsView.branchListView(allBranches);
        System.out.println("Enter ID of Branch You Wanna Remove :");
        id = ApplicationContext.longFromScannerReturn(id);
        long finalId = id;

        try {
            Branch selectedBranch = allBranches.stream().filter(branch -> branch.getId() == finalId)
                    .collect(Collectors.toList()).get(0);

            System.out.println("⚠ Are You Sure You Wanna Remove This Branch ? (y/n)");
            String answer = ApplicationContext.getTextScanner().next();
            if (answer.equals("y")) {
                ApplicationContext.getBranchService().delete(selectedBranch);
                System.out.println("*** Branch Deleted Successfully ***");
            }
        } catch (Exception e) {
            System.out.println("----> Wrong Id <----");
        }
    }

    private static void addEmployee(Manager activeManager) {
        String firstname;
        String lastname;
        String nationalCode;
        Date birthday;
        String username;
        String password;
        UserType userType;
        String bday;
        Branch branch;

        System.out.println("Enter Employee's First Name :");
        firstname = ApplicationContext.getTextScanner().next();

        System.out.println("Enter Employee's Last Name :");
        lastname = ApplicationContext.getTextScanner().next();

        do {
            System.out.println("Enter Employee's National Code (10 Digits) :");
            nationalCode = ApplicationContext.getTextScanner().next();
        } while (!Authentication.nationalCodeAuthenticate(nationalCode));

        do {
            System.out.println("Enter Employee's Birthday (yyyy-mm-dd) :");
            bday = ApplicationContext.getTextScanner().next();
        } while (!Authentication.birthdayAuthenticate(bday));
        birthday = Date.valueOf(bday);

        System.out.println("Enter Employee's Username :");
        username = ApplicationContext.getTextScanner().next();

        password = nationalCode;

        System.out.println(" *** National Code is Set as Initial Password ***");

        userType = UserType.EMPLOYEE;

        ApplicationContext.getEmployeeService().save(new Employee(firstname, lastname, nationalCode,
                birthday, username, password, userType, activeManager.getBranch()));

    }

    private static void viewBranchEmployees(Manager activeManager) {
        List<Employee> employees = ApplicationContext
                .getEmployeeService().findAllByBranchId(activeManager.getBranch());
        DetailsView.employeeViewList(employees);
    }

    private static void removeEmployee(Manager activeManager) {
        long id = 0;
        List<Employee> employees = ApplicationContext
                .getEmployeeService().findAllByBranchId(activeManager.getBranch());
        viewBranchEmployees(activeManager);

        System.out.println("Enter ID of Employee You Wanna Remove :");
        id = ApplicationContext.longFromScannerReturn(id);
        long finalId = id;

        try {
            Employee employee = employees.stream().filter(em -> em.getId() == finalId)
                    .collect(Collectors.toList()).get(0);

            System.out.println("⚠ Are You Sure You Wanna Remove This Employee ? (y/n)");
            String answer = ApplicationContext.getTextScanner().next();
            if (answer.equals("y")) {
                ApplicationContext.getEmployeeService().delete(employee);
                System.out.println("*** Employee Deleted Successfully ***");
            }
        } catch (Exception e) {
            System.out.println("----> Wrong Id <----");
        }
    }

    private static void viewBranchCustomers(Manager activeManager) {
        List<Account> accounts = ApplicationContext
                .getAccountService().getAccountsByBranch(activeManager.getBranch());
        List<Customer> customers = new ArrayList<>();
        for (Account account : accounts) {
            customers.add(account.getCustomer());
        }
        DetailsView.customerViewList(customers);
    }

    private static void addCustomer(Employee activeEmployee) {
        String firstname;
        String lastname;
        String nationalCode;
        Date birthday;
        String username;
        String password;
        UserType userType;
        String bday;

        System.out.println("Enter Customer's First Name :");
        firstname = ApplicationContext.getTextScanner().next();

        System.out.println("Enter Custom's Last Name :");
        lastname = ApplicationContext.getTextScanner().next();

        do {
            System.out.println("Enter Custom's National Code (10 Digits) :");
            nationalCode = ApplicationContext.getTextScanner().next();
        } while (!Authentication.nationalCodeAuthenticate(nationalCode));

        do {
            System.out.println("Enter Custom's Birthday (yyyy-mm-dd) :");
            bday = ApplicationContext.getTextScanner().next();
        } while (!Authentication.birthdayAuthenticate(bday));
        birthday = Date.valueOf(bday);

        System.out.println("Enter Custom's Username :");
        username = ApplicationContext.getTextScanner().next();

        password = nationalCode;

        System.out.println(" *** National Code is Set as Initial Password ***");

        userType = UserType.CUSTOMER;

        System.out.println("Now Please Add Customer's Account\n" +
                "===============================================================");
        Set<Account> customerAccounts = new HashSet<>();
        customerAccounts.add(addAccount(activeEmployee));

        Customer newCustomer = new Customer(firstname, lastname, nationalCode, birthday, username, password, userType, customerAccounts);

        try {
            ApplicationContext.getCustomerService().save(newCustomer);
            System.out.println("Customer Added Successfully");
        } catch (Exception e) {
            System.out.println("----> Customer Added Successfully");
        }

    }

    private static Account addAccount(Employee activeEmployee) {
        String accountNumber;
        double credit = 20000.0;
        Branch branch = activeEmployee.getBranch();

        do {
            System.out.println("Enter Account Number :");
            accountNumber = ApplicationContext.getTextScanner().next();
        } while (!Authentication.accountNumberAuthenticate(accountNumber));

        System.out.println("Now Please Add Account's Credit Card\n" +
                "===============================================================");
        CreditCard accountCreditCard = addCreditCard();

        return new Account(accountNumber, credit, true, accountCreditCard, branch);
    }

    private static CreditCard addCreditCard() {
        String cardNumber;
        String password;
        String cvv2;
        Date expireDate;

        do {
            System.out.println("Enter Card Number (16 Digits): ");
            cardNumber = ApplicationContext.getTextScanner().next();
        } while (!Authentication.cardNumberAuthenticate(cardNumber));

        do {
            System.out.println("Enter Card Password (4 Digits): ");
            password = ApplicationContext.getTextScanner().next();
        } while (!Authentication.cardPasswordAuthenticate(password));

        do {
            System.out.println("Enter Card CVV2 (4 Digits): ");
            cvv2 = ApplicationContext.getTextScanner().next();
        } while (!Authentication.cardcvv2Authenticate(cvv2));

        expireDate = Date.valueOf(LocalDate.now().plusYears(4));

        return new CreditCard(cardNumber, password, cvv2, expireDate);
    }

    private static void viewListOfCustomerAccounts(Employee activeEmployee) {

        List<Account> branchAccounts = ApplicationContext.getAccountService()
                .getAccountsByBranch(activeEmployee.getBranch());
        long id = 0L;
        List<Customer> customers = new ArrayList<>();
        for (Account account : branchAccounts) {
            customers.add(account.getCustomer());
        }
        DetailsView.customerViewList(customers);

        try {
            System.out.println("Enter Customer ID You Wanna View Accounts :");
            id = ApplicationContext.longFromScannerReturn(id);
            long finalId = id;

            Customer chosenCustomer = customers.stream().filter(customer -> customer.getId() == finalId)
                    .collect(Collectors.toList()).get(0);

            DetailsView.customerAccountsListView(chosenCustomer.getAccounts());
        } catch (IndexOutOfBoundsException e){
            System.out.println("----> Wrong Id <----");;
        }


    }

    private static Customer changeProfilePassword(Customer activeCustomer) {
        DetailsView.customerProfileView(activeCustomer);

        String password;
        System.out.println("Enter Your Present Password :");
        password = ApplicationContext.getTextScanner().next();
        if (activeCustomer.getPassword().equals(password)) {
            System.out.println("Enter Your New Password :");
            password = ApplicationContext.getTextScanner().next();
            activeCustomer.setPassword(password);
            activeCustomer = ApplicationContext.getCustomerService().save(activeCustomer);
            System.out.println("*** Your Password Changed Successfully ***");
        } else {
            System.out.println("----> Wrong Entered Password <----");
        }
        return activeCustomer;
    }

    private static void setOrChangeSecondPassword(Customer activeCustomer) {
        String accountNumber;
        Set<Account> accounts = activeCustomer.getAccounts();
        DetailsView.customerAccountsListView(accounts);

        System.out.println("Enter Account Number : ");
        accountNumber = ApplicationContext.getTextScanner().next();
        for (Account account : accounts) {
            if (accountNumber.equals(account.getAccountNumber())) {
                CreditCard creditCard = accounts.stream().filter(acc -> acc.getAccountNumber().equals(accountNumber))
                        .collect(Collectors.toList()).get(0).getCreditcard();
                String secondPassword;
                if (creditCard.getSecondPassword() == null) {
                    System.out.println("You did not Set, Enter Your Password : ");
                    secondPassword = ApplicationContext.getTextScanner().next();
                    creditCard.setSecondPassword(secondPassword);
                    ApplicationContext.getCreditCardService().save(creditCard);
                    System.out.println("*** Second Password Set Successfully ***");
                } else {
                    System.out.println("Enter Your Present Password : ");
                    secondPassword = ApplicationContext.getTextScanner().next();
                    if (secondPassword.equals(creditCard.getSecondPassword())) {
                        System.out.println("Enter Your New Password : ");
                        secondPassword = ApplicationContext.getTextScanner().next();
                        creditCard.setSecondPassword(secondPassword);
                        ApplicationContext.getCreditCardService().save(creditCard);
                        System.out.println("*** Second Password Changed Successfully ***");
                    } else {
                        System.out.println("----> Wrong Password <----");
                    }

                }
            } else {
                System.out.println("----> Wrong Account Number <----");
            }
        }
    }

    private static void transferMoney(Customer activeCustomer) {

        if (authenticationForTransfer(activeCustomer)) {
            Account destinationAccount =
                    ApplicationContext.getCreditCardService().findByCardNumber(destinationCardNumber).getAccount();
            Customer destinationCustomer = destinationAccount.getCustomer();

            double customerInitCredit = customerAccount.getCredit();
            double destinationInitCredit = destinationAccount.getCredit();

            customerAccount.setCredit(customerInitCredit - transferCredit - 600.0);
            ApplicationContext.getAccountService().save(customerAccount);

            destinationAccount.setCredit(destinationInitCredit + transferCredit);
            ApplicationContext.getAccountService().save(destinationAccount);

            System.out.println("*** Transfer Was Done ***");;


        }

    }

    private static Boolean authenticationForTransfer(Customer activeCustomer) {
        Set<Account> accounts = activeCustomer.getAccounts();
        List<CreditCard> creditCards = new ArrayList<>();
        for (Account account : accounts) {
            creditCards.add(account.getCreditcard());
        }

        String customerCardNumber;
        String cvv2;
        String secondPassword;
        String eday;
        Date expireDate;


        System.out.println("Enter Your Card Number : ");
        customerCardNumber = ApplicationContext.getTextScanner().next();
        String finalCardNumber = customerCardNumber;
        if (creditCards.stream().filter(creditCard -> creditCard.getCardNumber().equals(finalCardNumber))
                .collect(Collectors.toList()).get(0) == null) {
            System.out.println("----> You Don't Have This Card Number <----");
            return false;
        } else if(!creditCards.stream().filter(creditCard -> creditCard.getCardNumber().equals(finalCardNumber))
                .collect(Collectors.toList()).get(0).getAccount().getActive()){
            System.out.println("----> Your Account Is Not Active <----");
        }
        else {
            CreditCard customerCard = creditCards.stream().
                    filter(creditCard -> creditCard.getCardNumber().equals(finalCardNumber))
                    .collect(Collectors.toList()).get(0);

            System.out.println("Enter Card Number You Wanna Transfer Money : ");
            destinationCardNumber = ApplicationContext.getTextScanner().next();

            if (ApplicationContext.getCreditCardService().existByCardNumber(destinationCardNumber)) {
                if (checkCardDetails(customerCard)) {
                    System.out.println("How Much You Wanna Transfer ?");
                    transferCredit = ApplicationContext.getNumericScanner().nextDouble();
                    customerAccount = customerCard.getAccount();

                    if (transferCredit + 600.0 > customerAccount.getCredit()) {
                        System.out.println("----> Lack of Credit in Your Account <----");
                        return false;
                    }
                    return true;
                }
            } else {
                System.out.println("----> Wrong Destination Card Number <----");
                return false;
            }
        }
        return false;
    }

    private static Boolean checkCardDetails
            (CreditCard creditCard) {
        String cvv2;
        String secondPassword;
        String eday;
        Date expireDate;
        boolean result = true;
        int count = 0;

        while (count <= 3){
            count++;

            System.out.println("Enter Your Card CVV2 : ");
            cvv2 = ApplicationContext.getTextScanner().next();
            do {
                System.out.println("Enter Your Expire Date (yyyy-mm-dd): ");
                eday = ApplicationContext.getTextScanner().next();
            } while (!Authentication.birthdayAuthenticate(eday));
            expireDate = Date.valueOf(eday);

            System.out.println("Enter Your Card Second Password : ");
            secondPassword = ApplicationContext.getTextScanner().next();

            if (!creditCard.getCvv2().equals(cvv2)) {
                System.out.println("----> Wrong CVV2 <----");
                result = false;
            } else if (!creditCard.getExpireDate().equals(expireDate)) {
                System.out.println("----> Wrong Expire Date <----");
                result = false;
            } else if (!creditCard.getSecondPassword().equals(secondPassword)) {
                System.out.println("----> Wrong Second Password <----");
                result = false;
            }
            if (result) break;
        }
        if (count == 3){
            customerAccount.setActive(false);
            System.out.println("----> Your Account Details Entered Wrong More than 3 Times\n" +
                    "Your Account Is Deactivated, Please Contact Our Employees <----");
        }
        return result;
    }

}
