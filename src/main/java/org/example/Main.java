package org.example;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Book> libraryContent = new ArrayList<Book>();
        LoadBooks loadBooks = new LoadBooks();

        System.out.println("   - Welcome to Syd's Library -");
        libraryContent = loadBooks.readCSVFile();
    }
}