package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Book> libraryContent = new ArrayList<Book>();
        LoadBookLibraryCSV loadBookLibraryCSV = new LoadBookLibraryCSV();
        LoadBookLibraryJSON loadBookLibraryJSON = new LoadBookLibraryJSON();
        SaveBookLibrary saveBookLibrary = new SaveBookLibrary();

        System.out.println("   - Welcome to Syd's Library -");
 //       libraryContent = loadBookLibraryCSV.readCSVFile();
        libraryContent = loadBookLibraryJSON.readJSONFile();
        System.out.println("\n    " + libraryContent.size() + " books were loaded.");
         if (libraryContent.size() != 0) saveBookLibrary.saveBookLibraryToJSONFile(libraryContent);
    }
}