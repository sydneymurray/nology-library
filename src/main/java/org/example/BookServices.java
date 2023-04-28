package org.example;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BookServices {
    private String textInput;
    private int selection;
    private static Scanner keyboardInput = new Scanner(System.in);
    private SaveBookLibrary saveBookLibrary = new SaveBookLibrary();

    public void displayAllAvailableBooks(ArrayList<Book> books) {
        System.out.println("  ID  Author                 Title                                                   Genre" +
                "        Publisher");
        for (Book book : books) {
            if (book.getLoanerID() == 0) {
                System.out.printf("%4d  %-22s %-55s %-12s %-15s %n",
                        book.getId(), book.getAuthor(), book.getTitle(), book.getGenre(), book.getPublisher());
            }
        }
        returnToMainMenu();
    }

    public void searchAvailableBooks(ArrayList<Book> books) {
        System.out.println("\n     Please enter a search term");
        try {
            textInput = keyboardInput.nextLine();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            return;
        }
        textInput = textInput.toUpperCase();
        System.out.println("  ID  Author                 Title                                                   Genre" +
                "        Publisher");
        for (Book book : books) {
            if (book.getLoanerID() == 0 &&
                    (book.getAuthor().toUpperCase().contains(textInput) ||
                            book.getTitle().toUpperCase().contains(textInput))) {
                System.out.printf("%4d  %-22s %-55s %-12s %-15s %n",
                        book.getId(), book.getAuthor(), book.getTitle(), book.getGenre(), book.getPublisher());
            }
        }
        returnToMainMenu();
    }

    public void borrowABook(Member loggedInMember, ArrayList<Book> books) {
        Book selectedBook = null;
        System.out.println("\n     Please enter the books ID");
        try {
            selection = keyboardInput.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("\nIncorrect selection");
            return;
        }

        for (Book book : books) {
            if (book.getId() == selection && book.getLoanerID() == 0) {
                selectedBook = book;
                break;
            }
        }
        if (selectedBook == null){
            System.out.println("\n     The selected book is not available");
            returnToMainMenu();
            return;
        }

        System.out.println("\n    Please confirm selected book: " +
                selectedBook.getAuthor() + " " + selectedBook.getTitle());
        System.out.println("\n    1) Check out the book");
        System.out.println("\n    2) Return to the main menu");
        try {
            selection = keyboardInput.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("\nIncorrect selection");
            return;
        }
        if (selection == 1) {
            selectedBook.incrementTimesLoaned();
            selectedBook.setLoanerID(loggedInMember.getId());
            System.out.println("      " + selectedBook.getAuthor() + " " + selectedBook.getTitle() +
                    " has been checked out in your name");
            saveBookLibrary.saveBookLibraryToJSONFile(books);
        }
        returnToMainMenu();
    }

    public void returnABook(Member loggedInMember, ArrayList<Book> books) {
        Boolean returnBook = false;

        System.out.println("\n     Please enter the book ID that you wish to return.");
        for (Book book : books) {
            if (book.getLoanerID() == loggedInMember.getId()) {
                System.out.printf("%4d  %-22s %-55s %-12s %-15s %n",
                        book.getId(), book.getAuthor(), book.getTitle(), book.getGenre(), book.getPublisher());
            }
        }

        try {
            selection = keyboardInput.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("\nIncorrect selection");
            return;
        }

        for (Book book : books) {
            if (book.getId() == selection && book.getLoanerID() == loggedInMember.getId()) {
                returnBook = true;
                book.setLoanerID(0);
                saveBookLibrary.saveBookLibraryToJSONFile(books);
                System.out.println("      You have successfully returned " + book.getAuthor() + " " + book.getTitle());
                break;
            }
        }

        if (!returnBook) System.out.println("\n     Book ID: " + selection + " has not been registered to you.");
        returnToMainMenu();
    }

    private void returnToMainMenu(){
        System.out.println("\n     Press enter to return to the main menu");
        try {
            textInput = keyboardInput.nextLine();
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }
}
