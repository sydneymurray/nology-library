package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LibraryInService {
    private Member loggedInMember;
    private int selection;
    private static Scanner keyboardInput = new Scanner(System.in);

    public void inService() {
        while (true) {
            if (loggedInMember == null) displayLoggedOutMenu();
            else displayLoggedInMenu();
        }
    }
    private void displayLoggedOutMenu() {
        System.out.println("\n\n     Welcome to Syds Library. Please select an option: ");
        System.out.println("\n    1) Sign Up: ");
        System.out.println("\n    2) Login");
        try {
            selection = keyboardInput.nextInt();
        } catch (InputMismatchException e) {
            //e.printStackTrace();
            System.out.println("\nIncorrect selection");
            return;
        }
//        switch (selection) {
//            case 1:
//                financialInformation.registerACustomer(RegisterACustomer.RegisterACustomer());
//                break;
//            case 2:
//                loggedInCustomer = LoginCustomer.LoginCustomer(financialInformation);
//                break;
//            default:
//                // code block
//        }
    }

    private void displayLoggedInMenu() {
//        customerAccounts = financialInformation.getCustomerAccounts(loggedInCustomer);
//        System.out.println("\n\n     Welcome " + loggedInCustomer.getUsername() + ". Please select an option: \n");
//        displayCustomerAccounts();
//
//        System.out.println("\n    1) Pay a Customer");
//        System.out.println("\n    2) Deposit");
//        System.out.println("\n    3) Withdraw");
//        System.out.println("\n    4) Display Statement");
//        System.out.println("\n    5) Create an Account");
//        System.out.println("\n    6) Logout");
//        try {
//            selection = keyboardInput.nextInt();
//        } catch (InputMismatchException e) {
//            System.out.println("\nIncorrect selection");
//            return;
//        }
//        switch (selection) {
//            case 1:
//                PayACustomer.PayACustomer(loggedInCustomer, customerAccounts, financialInformation);
//                break;
//
//            default:
//                // code block
//        }
    }
}
