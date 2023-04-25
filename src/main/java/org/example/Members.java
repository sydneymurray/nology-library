package org.example;

import java.util.Random;

public class Members {
    private int id;
    private String name;
    private String email;
    private String password;
    private int[] loanedBooks;

    public Members(String name, String email, String password) {
        Random random = new Random();
        this.id = 1000 + random.nextInt(8999);
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int[] getLoanedBooks() {
        return loanedBooks;
    }

    public int getId() {
        return id;
    }

    public void setLoanedBooks(int[] loanedBooks) {
        this.loanedBooks = loanedBooks;
    }
}
