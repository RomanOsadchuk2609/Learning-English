package com.dariahaze.learning_english.model;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class FlashCard implements Serializable {
    @Exclude
    private String key;
    private int number;
    private String frontText;
    private String backText;

    public FlashCard() {
    }

    public FlashCard(String frontText, String backText) {
        this.frontText = frontText;
        this.backText = backText;
    }

    public String getFrontText() {
        return frontText;
    }

    public void setFrontText(String frontText) {
        this.frontText = frontText;
    }

    public String getBackText() {
        return backText;
    }

    public void setBackText(String backText) {
        this.backText = backText;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Exclude
    public String getKey() {
        return key;
    }

    @Exclude
    public void setKey(String key) {
        this.key = key;
    }
}
