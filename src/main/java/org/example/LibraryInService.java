package org.example;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LibraryInService {

    private Member loggedInMember;
    private int selection;
    private LibraryInformation libraryInformation = new LibraryInformation();
    private MemberServices memberServices = new MemberServices();
    private static Scanner keyboardInput = new Scanner(System.in);
    private int bookTotal;

    public void inService() {

        bookTotal = libraryInformation.retrieveLibraryData();
        if (bookTotal == 0) {
            System.out.println("Unable to load Book files.");
            return;
        }

        System.out.println("     - Welcome to Syd's Library -\n");
        while (true) {
            if (loggedInMember == null) displayLoggedOutMenu();
            else displayLoggedInMenu();
        }
    }

    private void displayLoggedOutMenu() {
        System.out.println("\n\n     Welcome to Syds Library. Please select an option: ");
        System.out.println("           We has a selection of " + bookTotal + " books.\n");
        System.out.println("\n    1) Sign Up: ");
        System.out.println("\n    2) Login");
        try {
            selection = keyboardInput.nextInt();
        } catch (InputMismatchException e) {
            //e.printStackTrace();
            System.out.println("\nIncorrect selection");
            return;
        }
        switch (selection) {
            case 1:
                memberServices.registerANewMember(libraryInformation);
                break;
            case 2:
                loggedInMember = memberServices.LoginMember(libraryInformation.getLibraryMembers());
                break;
            default:
                // code block
        }
    }

    private void displayLoggedInMenu() {
        System.out.println("\n\n     Welcome " + loggedInMember.getName() + ". Please select an option: \n");

        System.out.println("\n    1) Borrow a book");
        System.out.println("\n    2) Return a book");
        System.out.println("\n    3) Logout");
        System.out.println("\n    4) Upgrade membership to Admin.");
        if (loggedInMember.getAdministrator()) {
            System.out.println("\n    5) Display books on loan");
            System.out.println("\n    6) Display books that have been lent");
        }

        try {
            selection = keyboardInput.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("\nIncorrect selection");
            return;
        }
        switch (selection) {
            case 1:

                break;
            case 2:

                break;
            case 3:
                loggedInMember = null;
                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
            default:
                // code block
        }
    }
}
