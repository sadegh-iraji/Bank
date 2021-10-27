package ir.maktab.bank.util;

public class Authentication {

    public static Boolean branchCodeAuthenticate(String branchCode) {
        if (branchCode.matches("[0-9]{4}")) {
            return true;
        } else {
            System.out.println("----> Branch Code Must Contains Only 4 Digits <----");
        }
        return false;
    }

    public static Boolean nationalCodeAuthenticate(String nationalCode) {
        if (nationalCode.matches("[0-9]{10}")) {
            return true;
        } else {
            System.out.println("----> National Code Must Contains Only 10 Digits <----");
        }
        return false;
    }

    public static Boolean birthdayAuthenticate(String birthday) {
        if (birthday.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            return true;
        } else {
            System.out.println("----> Birthday Must Follows yyyy-mm-dd <----");
        }
        return false;
    }

    public static Boolean accountNumberAuthenticate(String accountNumber) {
        if (accountNumber.matches("[0-9]{5}")) {
            return true;
        } else {
            System.out.println("----> Account Number Must Contains Only 5 Digits <----");
        }
        return false;
    }

    public static Boolean cardNumberAuthenticate(String cardNumber) {
        if (cardNumber.matches("[0-9]{16}")) {
            return true;
        } else {
            System.out.println("----> Card Number Must Contains Only 16 Digits <----");
        }
        return false;
    }

    public static Boolean cardPasswordAuthenticate(String cardPassword) {
        if (cardPassword.matches("[0-9]{4}")) {
            return true;
        } else {
            System.out.println("----> Card Password Must Contains Only 4 Digits <----");
        }
        return false;
    }

    public static Boolean cardcvv2Authenticate(String cvv2) {
        if (cvv2.matches("[0-9]{4}")) {
            return true;
        } else {
            System.out.println("----> Card CVV2 Must Contains Only 4 Digits <----");
        }
        return false;
    }
}
