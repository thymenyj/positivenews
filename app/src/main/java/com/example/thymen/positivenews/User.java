package com.example.thymen.positivenews;

import java.util.ArrayList;

public class User {
    String name;
    String email;
    ArrayList<String> preferences;
    ArrayList<String> savedArticles;

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

    public ArrayList<String> getPreferences() {
        return preferences;
    }

    public void setPreferences(ArrayList<String> preferences) {
        this.preferences = preferences;
    }

    public ArrayList<String> getSavedArticles() {
        return savedArticles;
    }

    public void setSavedArticles(ArrayList<String> savedArticles) {
        this.savedArticles = savedArticles;
    }

}

