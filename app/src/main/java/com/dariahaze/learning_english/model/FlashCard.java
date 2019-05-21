package com.dariahaze.learning_english.model;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.Objects;

public class FlashCard implements Serializable {
    @Exclude
    private String key;
    @Exclude
    private String path;
    private int number;
    private String frontText;
    private String backText;

    public FlashCard() {
    }

    public FlashCard(String frontText, String backText) {
        this.frontText = frontText;
        this.backText = backText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlashCard)) return false;
        FlashCard flashCard = (FlashCard) o;
        return Objects.equals(getKey(), flashCard.getKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey());
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

    @Exclude
    public String getPath() {
        return path;
    }

    @Exclude
    public void setPath(String path) {
        this.path = path;
    }
}
