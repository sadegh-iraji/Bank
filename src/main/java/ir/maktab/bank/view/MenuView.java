package ir.maktab.bank.view;

public class MenuView {

    public static void adminMenuView(){
        System.out.println(
                "1. Add a Branch\n" +
                        "2. Remove a Branch\n" +
                        "3. Sign Out"
        );
    }
    public static void managerMenuView(){
        System.out.println(
                "1. Add an Employee\n" +
                        "2. Remove an Employee\n" +
                        "3. Watch List of Employees\n" +
                        "4. Watch List of Customers\n" +
                        "5. Sign Out"
        );
    }

    public static void employeeMenuView(){
        System.out.println(
                "1. Add a Customer\n" +
                        "2. Watch List of a Customer's Accounts\n" +
                        "3. Edit a Customer's Account\n" +
                        "4. Sign Out"
        );
    }

    public static void customerMenuView(){
        System.out.println(
                "1. Edit Your Profile Password\n " +
                        "2. Set or Edit Credit Card's Second Password\n" +
                        "3. Transfer Money\n" +
                        ""
        );
    }


}
