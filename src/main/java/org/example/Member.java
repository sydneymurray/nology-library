package org.example;

import java.util.Random;

public class Member {
    private int id;
    private String name;
    private String email;
    private String password;
    private Boolean administrator;

    public Member(String name, String email, String password) {
        Random random = new Random();
        this.id = 1000 + random.nextInt(8999);
        this.name = name;
        this.email = email;
        this.password = password;
        this.administrator = false;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Boolean administrator) {
        this.administrator = administrator;
    }
}
