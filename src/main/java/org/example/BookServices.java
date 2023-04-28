package org.example;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class BookServices {
    String textInput;
    private static Scanner keyboardInput = new Scanner(System.in);
    public void displayAllAvailableBooks(ArrayList<Book> books){
        System.out.println("  ID  Author                 Title                                                   Genre" +
                "        Publisher");
        for (Book book: books) {
            if(book.getLoanerID() == 0) {
                System.out.printf("%4d  %-22s %-55s %-12s %-15s %n",
                        book.getId(), book.getAuthor(), book.getTitle(), book.getGenre(), book.getPublisher());
            }
        }
        System.out.println("\n     Press enter to return to the main menu");
        try {
            textInput = keyboardInput.nextLine();
        } catch (Exception e) {
            return;
        }
    }
}
