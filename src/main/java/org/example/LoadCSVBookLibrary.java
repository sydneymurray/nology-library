package org.example;

import java.io.*;
import java.util.ArrayList;

public class LoadCSVBookLibrary {
    private ArrayList<Book> books = new ArrayList<Book>();

    public ArrayList<Book> readCSVFile(File bookData) {
        String dataLine;
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(bookData));
            fileReader.readLine();
            while ((dataLine = fileReader.readLine()) != null) {
                Book book = processDataLine(dataLine.split(","));
                books.add(book);
                System.out.printf("%4d %4d %-22s %-55s %-12s %-15s %n",
                        book.getId(), book.getTimesLoaned(), book.getAuthor(), book.getTitle(), book.getGenre(),
                        book.getPublisher());
            }
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            System.out.println("CSV file not found");
        } catch (IOException e) {
            // throw new RuntimeException(e);
            System.out.println("Could not load CSV file due to an IO error");
        }
        return books;
    }

    private Book processDataLine(String[] dataLineArr) {
        Book book = null;
        if (dataLineArr[1].contains("\"") && dataLineArr[3].contains("\"") && !dataLineArr[3].contains("John Doe")) {
            book = new Book(
                    Integer.parseInt(dataLineArr[0]),
                    0,
                    dataLineArr[2].replace("\"", "").replace(" ", "") +
                            " " + dataLineArr[1].replace("\"", ""),
                    dataLineArr[4].replace("\"", "").replace(" ", "") +
                            " " + dataLineArr[3].replace("\"", ""),
                    dataLineArr[5],
                    dataLineArr[6],
                    dataLineArr[7],
                    0
            );
        } else if (dataLineArr[1].contains("\"")) {
            book = new Book(
                    Integer.parseInt(dataLineArr[0]),
                    0,
                    dataLineArr[2].replace("\"", "").replace("", "") +
                            " " + dataLineArr[1].replace("\"", ""),
                    dataLineArr[3].replace("\"", ""),
                    dataLineArr[4],
                    dataLineArr[5],
                    dataLineArr[6],
                    0
            );
        } else if (dataLineArr[2].contains("\"")) {
            book = new Book(
                    Integer.parseInt(dataLineArr[0]),
                    0,
                    dataLineArr[1],
                    dataLineArr[3].replace("\"", "").replace(" ", "") +
                            " " + dataLineArr[2].replace("\"", ""),
                    dataLineArr[4],
                    dataLineArr[5],
                    dataLineArr[6],
                    0
            );
        } else {
            book = new Book(
                    Integer.parseInt(dataLineArr[0]),
                    0,
                    dataLineArr[1],
                    dataLineArr[2],
                    dataLineArr[3],
                    dataLineArr[4],
                    dataLineArr[5],
                    0
            );
        }
        return book;
    }
}
