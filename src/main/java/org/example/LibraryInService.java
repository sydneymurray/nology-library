package org.example;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LibraryInService {

    private Member loggedInMember;
    private int selection;
    private LibraryInformation libraryInformation = new LibraryInformation();
    private MemberServices memberServices = new MemberServices();
    private BookServices bookServices = new BookServices();
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
        System.out.println("\n     Welcome " + loggedInMember.getName() + ". Please select an option: \n");
        System.out.println("\n    1) Display all available books");
        System.out.println("\n    2) Search for a book");
        System.out.println("\n    3) Borrow a book");
        System.out.println("\n    4) Return a book");
        System.out.println("\n    5) Logout");
        System.out.println("\n    6) Upgrade membership to Admin.");
        if (loggedInMember.getAdministrator()) {
            System.out.println("\n    7) Display current books on loan");
            System.out.println("\n    8) Display books that have been lent");
        }

        try {
            selection = keyboardInput.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("\nIncorrect selection");
            return;
        }
        switch (selection) {
            case 1:
                bookServices.displayAllAvailableBooks(libraryInformation.getLibraryContent());
                break;
            case 2:
                bookServices.searchAvailableBooks(libraryInformation.getLibraryContent());
                break;
            case 3:
                bookServices.borrowABook(loggedInMember, libraryInformation.getLibraryContent());
                break;
            case 4:
                bookServices.returnABook(loggedInMember, libraryInformation.getLibraryContent());
                break;
            case 5:
                loggedInMember = null;
                break;
            case 6:
                memberServices.upgradeMembership(loggedInMember, libraryInformation.getLibraryMembers());
                break;
            case 7:
                bookServices.displayBooksOnLoan(loggedInMember, libraryInformation.getLibraryContent(),
                        libraryInformation.getLibraryMembers());
                break;
            case 8:
//                bookServices.displayBooksOnLoan(loggedInMember, libraryInformation.getLibraryContent(),
//                        libraryInformation.getLibraryMembers());
                break;
            default:
                // code block
        }
    }
}
