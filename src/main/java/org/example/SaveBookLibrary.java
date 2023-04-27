package org.example;

import java.io.*;
import java.util.ArrayList;

public class SaveBookLibrary {
    private String bookDataFilePath = "./src/main/resources/books_data.json";

    public void saveBookLibraryToJSONFile(ArrayList<Book> books) {
        Boolean jsonSeparator = false;
        File booksDataFile = new File(bookDataFilePath);
        if (booksDataFile.exists()) booksDataFile.delete();
        try {
            booksDataFile.createNewFile();
        } catch (Exception e) {
            System.out.println("Could not create " + bookDataFilePath);
        }

        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(bookDataFilePath));
            fileWriter.write("[");
            for (Book book : books) {
                fileWriter.write(jsonSeparator ? "," : "");
                fileWriter.write("\n  {   \n    \"id\":" + String.valueOf(book.getId()) + "," +
                        "\n    \"timesLoaned\":" + String.valueOf(book.getTimesLoaned()) + "," +
                        "\n    \"author\":" + "\"" + book.getAuthor() + "\"," +
                        "\n    \"title\":" + "\"" + book.getTitle() + "\"," +
                        "\n    \"genre\":" + "\"" + book.getGenre() + "\"," +
                        "\n    \"subGenre\":" + "\"" + book.getSubGenre() + "\"," +
                        "\n    \"publisher\":" + "\"" + book.getPublisher() + "\"," +
                        "\n    \"loanerID\":" + "" + String.valueOf(book.getLoanerID()) +
                        "\n  }"
                );
                jsonSeparator = true;
            }
            fileWriter.write("\n]\n");
            fileWriter.close();
        } catch (IOException e) {
            // throw new RuntimeException(e);
            System.out.println("Could not save books JSON file due to an IO error");
        }
    }
}
