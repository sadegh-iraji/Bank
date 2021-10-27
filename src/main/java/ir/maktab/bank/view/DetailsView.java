package ir.maktab.bank.view;

import ir.maktab.bank.domain.*;

import java.util.List;
import java.util.Set;

public class DetailsView {

    public static void branchListView(List<Branch> branches){

        for (Branch branch : branches) {
            System.out.println(
                    "ID : " + branch.getId() + "\n" +
                            "Branch Code : " + branch.getBranchCode() + "\n" +
                            "Branch Name : " + branch.getBranchName() + "\n" +
                            "---------------------------------------------------------"
            );
        }
    }

    public static void employeeViewList(List<Employee> employees){
        for (Employee employee : employees){
            System.out.println(
                    "ID : " + employee.getId() + "\n" +
                            "First Name : " + employee.getFirstname() + "\n" +
                            "Last Name : " + employee.getLastname() + "\n" +
                            "National Code : " + employee.getNationalCode() + "\n" +
                            "Birthday : " + employee.getBirthday() + "\n" +
                            "------------------------------------------------------"
            );
        }

    }

    public static void customerViewList(List<Customer> customers){
        for (Customer customer : customers){
            System.out.println(
                    "ID : " + customer.getId() + "\n" +
                            "First Name : " + customer.getFirstname() + "\n" +
                            "Last Name : " + customer.getLastname() + "\n" +
                            "National Code : " + customer.getNationalCode() + "\n" +
                            "Birthday : " + customer.getBirthday() + "\n" +
                            "------------------------------------------------------"
            );
        }

    }

    public static void customerAccountsListView(Set<Account> accounts){
        CreditCard creditCard;
        for (Account account : accounts){
            System.out.println(
                    "Account Number : " + account.getAccountNumber() + "\n" +
                    "Account Credit : " + account.getCredit() + "\n"
            );
            creditCard = account.getCreditcard();
            System.out.println(
                    "Credit Card Information : \n" +
                            "Card Number : " + creditCard.getCardNumber() + "\n" +
                            "Card CVV2 : " + creditCard.getCvv2() + "\n" +
                            "Card ExpireDate : " + creditCard.getExpireDate() + "\n" +
                            "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++"
            );
        }
    }

    public static void customerProfileView(Customer customer){
        System.out.println(
                "First Name : " + customer.getFirstname() + "\n" +
                "Last Name : " + customer.getLastname()+ "\n" +
                "Username : " + customer.getUsername()+ "\n" +
                "National Code : " + customer.getNationalCode()+ "\n" +
                "Birthday : " + customer.getBirthday()+ "\n"
        );
    }

}
