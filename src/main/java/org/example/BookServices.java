package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
        System.out.println("\n     Press enter to return to the main menu");
        keyboardInput.nextLine();
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
        System.out.println("\n     Press enter to return to the main menu");
        keyboardInput.nextLine();
    }

    public void borrowABook(Member loggedInMember, ArrayList<Book> books) {
        Book selectedBook = null;
        System.out.println("\n     Please enter the books ID");
        try {
            selection = keyboardInput.nextInt();
            keyboardInput.nextLine();
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
        if (selectedBook == null) {
            System.out.println("\n     The selected book is not available");
            System.out.println("\n     Press enter to return to the main menu");
            keyboardInput.nextLine();
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
        System.out.println("\n     Press enter to return to the main menu");
        keyboardInput.nextLine();
        keyboardInput.nextLine();
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
            keyboardInput.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("\n     You didn't select a book");
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

        if (!returnBook) {
            System.out.println("\n     Book ID: " + selection + " has not been registered to you.");
            System.out.println("\n     Press enter to return to the main menu");
            keyboardInput.nextLine();
            System.out.println("\n     Press enter to return to the main menu");
            keyboardInput.nextLine();
            return;
        }
        System.out.println("\n     Press enter to return to the main menu");
        keyboardInput.nextLine();
    }

    public void displayBooksOnLoan(Member loggedInMember, ArrayList<Book> books, ArrayList<Member> members) {
        if (!loggedInMember.getAdministrator()) return;

        File loanedBooksDataFile = new File("./src/main/resources/loaned_books.csv");
        if (loanedBooksDataFile.exists()) loanedBooksDataFile.delete();
        try {
            loanedBooksDataFile.createNewFile();
        } catch (Exception e) {
            System.out.println("Could not create " + loanedBooksDataFile);
            System.out.println("\n     Press enter to return to the main menu");
            keyboardInput.nextLine();
            return;
        }

        System.out.println("\n         Books currently on loan.  A copy is saved to loaned_books.csv");
        System.out.println("         MID Name            Email                      BID Author                 " +
                "Title                                    Genre        Publisher      ");
        String loanedBooksCSVData = "MemberID,Name,Email,BookID,Author,Title,Genre,Publisher\n";
        for (Book book : books) {
            if (book.getLoanerID() != 0) loanedBooksCSVData += displayLoanedBook(book, members);
        }
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(loanedBooksDataFile));
            fileWriter.write(loanedBooksCSVData);
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("Could not create " + loanedBooksDataFile);
        }
        System.out.println("\n     Press enter to return to the main menu");
        keyboardInput.nextLine();
    }

    private String displayLoanedBook(Book book, ArrayList<Member> members) {
        for (Member member : members) {
            if (book.getLoanerID() == member.getId()) {
                System.out.printf("        %4d %-15s %-25s %4d %-22s %-40s %-12s %-15s %n",
                        member.getId(), member.getName(), member.getEmail(), book.getId(), book.getAuthor(),
                        book.getTitle(), book.getGenre(), book.getPublisher());
                return member.getId() + "," + member.getName() + "," + member.getEmail() + "," +
                        book.getId() + "," + book.getAuthor() + "," + book.getTitle() + "," + book.getGenre() +
                        "," + book.getPublisher() + "\n";
            }
        }
        return "";
    }

    public void displayLentBooks(Member loggedInMember, ArrayList<Book> books) {
        if (!loggedInMember.getAdministrator()) return;

        File lentBooksDataFile = new File("./src/main/resources/lent_books.csv");
        if (lentBooksDataFile.exists()) lentBooksDataFile.delete();
        try {
            lentBooksDataFile.createNewFile();
        } catch (Exception e) {
            System.out.println("Could not create " + lentBooksDataFile);
            System.out.println("\n     Press enter to return to the main menu");
            keyboardInput.nextLine();
            return;
        }
        String lentBooksCSVData = "BookID,Lent,Author,Title,Genre,Publisher\n";
        System.out.println("\n         Books that have been lent. A copy is saved to lent_books.csv");
        System.out.println("          BID Lent Author                 " +
                "Title                                    Genre        Publisher      ");
        for (Book book : books) {
            if (book.getTimesLoaned() > 0) {
                System.out.printf("         %4d %4d %-22s %-40s %-12s %-15s %n",
                        book.getId(), book.getTimesLoaned(), book.getAuthor(),
                        book.getTitle(), book.getGenre(), book.getPublisher());
                lentBooksCSVData += book.getId() + "," + book.getTimesLoaned() + "," + book.getAuthor() + "," +
                        book.getTitle() + "," + book.getGenre() + "," + book.getPublisher() + "\n";
            }
        }
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(lentBooksDataFile));
            fileWriter.write(lentBooksCSVData);
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("Could not create " + lentBooksDataFile);
        }
        System.out.println("\n     Press enter to return to the main menu");
        keyboardInput.nextLine();
    }
}
