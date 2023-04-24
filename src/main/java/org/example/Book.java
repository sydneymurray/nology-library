package org.example;

public class Book {
    private int id;
    private int timesLoaned;
    private String title;
    private String author;
    private String genre;
    private String subGenre;
    private String publisher;

    public Book(int id, int timesLoaned, String title, String author, String genre, String subGenre, String publisher) {
        this.id = id;
        this.timesLoaned = timesLoaned;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.subGenre = subGenre;
        this.publisher = publisher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimesLoaned() {
        return timesLoaned;
    }

    public void setTimesLoaned(int timesLoaned) {
        this.timesLoaned = timesLoaned;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSubGenre() {
        return subGenre;
    }

    public void setSubGenre(String subGenre) {
        this.subGenre = subGenre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
