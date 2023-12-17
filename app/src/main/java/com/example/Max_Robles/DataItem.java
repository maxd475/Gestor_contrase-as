package com.example.Max_Robles;

public class DataItem {
    private String title;
    private String username;
    private String password;
    private String website;

    public DataItem(String title, String username, String password, String website) {
        this.title = title;
        this.username = username;
        this.password = password;
        this.website = website;
    }

    public String getTitle() {
        return title;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getWebsite() {
        return website;
    }
}
