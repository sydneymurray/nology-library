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
        //memberServices.displayMembers(libraryInformation.getLibraryMembers());
        System.out.println("\n\n     Welcome to Syds Library. Please select an option: ");
        System.out.println("           We have a selection of " + bookTotal + " books.\n");
        System.out.println("\n    1) Sign Up: ");
        System.out.println("\n    2) Login");
        try {
            selection = keyboardInput.nextInt();
            keyboardInput.nextLine();
        } catch (InputMismatchException e) {
            //e.printStackTrace();
            System.out.println("\nIncorrect selection");
            keyboardInput.nextLine();
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
        System.out.println("\n     Welcome " + loggedInMember.getName() + ". Please select an option: ");
        System.out.println("\n    1) Display all available books");
        System.out.println("\n    2) Search for a book");
        System.out.println("\n    3) Display loaned books");
        System.out.println("\n    4) Borrow a book");
        System.out.println("\n    5) Return a book");
        System.out.println("\n    6) Logout");
        System.out.println("\n    7) Upgrade membership to Admin.");
        if (loggedInMember.getAdministrator()) {
            System.out.println("\n    8) Display all books currently books on loan");
            System.out.println("\n    9) Display all books that have ever been lent out");
        }

        try {
            selection = keyboardInput.nextInt();
            keyboardInput.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("\nIncorrect selection");
            keyboardInput.nextLine();
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
                bookServices.displayLoanedBooks(loggedInMember, libraryInformation.getLibraryContent());
                break;
            case 4:
                bookServices.borrowABook(loggedInMember, libraryInformation.getLibraryContent());
                break;
            case 5:
                bookServices.returnABook(loggedInMember, libraryInformation.getLibraryContent());
                break;
            case 6:
                loggedInMember = null;
                break;
            case 7:
                memberServices.upgradeMembership(loggedInMember, libraryInformation.getLibraryMembers());
                break;
            case 8:
                bookServices.displayBooksOnLoan(loggedInMember, libraryInformation.getLibraryContent(),
                        libraryInformation.getLibraryMembers());
                break;
            case 9:
                bookServices.displayLentBooks(loggedInMember, libraryInformation.getLibraryContent());
                break;
            default:
                // code block
        }
    }
}
