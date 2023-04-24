package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadBooks {
    ArrayList<Book> books = new ArrayList<Book>();
    String productFilePath = "./src/main/resources/books_data.csv";


    public ArrayList<Book> readCSVFile() {
        String dataLine;
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(productFilePath));
            fileReader.readLine();
            while ((dataLine = fileReader.readLine()) != null) {
                Book book = processDataLine(dataLine.split(","));
                if (book != null)
                    System.out.println(book.getId() + " " + book.getAuthor() + "      " + book.getTitle());
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
                    dataLineArr[2].replace("\"", "") + " " + dataLineArr[1].replace("\"", ""),
                    dataLineArr[4].replace("\"", "") + " " + dataLineArr[3].replace("\"", ""),
                    dataLineArr[5],
                    dataLineArr[6],
                    dataLineArr[7]
            );
        } else if (dataLineArr[1].contains("\"")) {
            book = new Book(
                    Integer.parseInt(dataLineArr[0]),
                    0,
                    dataLineArr[2].replace("\"", "") + " " + dataLineArr[1].replace("\"", ""),
                    dataLineArr[3].replace("\"", ""),
                    dataLineArr[4],
                    dataLineArr[5],
                    dataLineArr[6]
            );
        } else if (dataLineArr[2].contains("\"")) {
            book = new Book(
                    Integer.parseInt(dataLineArr[0]),
                    0,
                    dataLineArr[1],
                    dataLineArr[3].replace("\"", "") + " " + dataLineArr[2].replace("\"", ""),
                    dataLineArr[4],
                    dataLineArr[5],
                    dataLineArr[6]
            );
        } else {
            book = new Book(
                    Integer.parseInt(dataLineArr[0]),
                    0,
                    dataLineArr[1],
                    dataLineArr[2],
                    dataLineArr[3],
                    dataLineArr[4],
                    dataLineArr[5]
            );
        }
        return book;
    }
}
