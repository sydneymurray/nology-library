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
                ;
                break;
            default:
                // code block
        }


    }

    private void displayLoggedInMenu() {

    }

    public void setLoggedInMember(Member loggedInMember) {
        this.loggedInMember = loggedInMember;
    }

    public void saveBookData(ArrayList<Book> books) {

    }
}
