package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadJSONBookLibrary {
    private ArrayList<Book> books = new ArrayList<Book>();
    private String bookDataFile = "./src/main/resources/books_data.json";

    public ArrayList<Book> readJSONFile() {
        String JSONString = buildJSONString();
        JSONString = JSONString.replace("[", "");
        JSONString = JSONString.replace("]", "");
        String[] JSONStringArr = JSONString.split("},  ");
        for (String JSON : JSONStringArr) {
            books.add(createABook(JSON));
        }
        return books;
    }

    private Book createABook(String JSON) {
        JSON = JSON.replace("{    ", "");
        JSON = JSON.replace("}", "");
        String[] bookKeys = JSON.split(",");
        Book book = new Book(
                Integer.parseInt(getKeyValue(bookKeys[0])),
                Integer.parseInt(getKeyValue(bookKeys[1])),
                getKeyValue(bookKeys[3]),
                getKeyValue(bookKeys[2]),
                getKeyValue(bookKeys[4]),
                getKeyValue(bookKeys[5]),
                getKeyValue(bookKeys[6]),
                Integer.parseInt(getKeyValue(bookKeys[7]))
        );
//        System.out.printf("%4d %4d %-22s %-55s %-12s %-15s %n",
//                book.getId(), book.getTimesLoaned(), book.getAuthor(), book.getTitle(), book.getGenre(),
//                book.getPublisher());
        return book;
    }

    private String getKeyValue(String key) {
        key = key.replace("\"", "");
        String[] value = key.split(":");
        if (value.length == 1) return "";
        if (value[1].contains("  "))
            value[1] = value[1].replace("  ", "");
        return value[1];
    }

    private String buildJSONString() {
        String JSONString = "";
        String dataLine;
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(bookDataFile));
            while ((dataLine = fileReader.readLine()) != null) {
                JSONString += dataLine;
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            System.out.println("JSON file not found");
        } catch (IOException e) {
            // throw new RuntimeException(e);
            System.out.println("Could not load JSON file due to an IO error");
        }
        return JSONString;
    }
}
