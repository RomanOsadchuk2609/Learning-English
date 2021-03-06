package com.dariahaze.learning_english.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.Objects;

@IgnoreExtraProperties
public class CardGroup implements Serializable {
    @Exclude
    private String key;
    @Exclude
    private String path;

    private String name;
    private int size;
    private int maxCardNumber;

    public CardGroup() {
    }

    public CardGroup(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getMaxCardNumber() {
        return maxCardNumber;
    }

    public void setMaxCardNumber(int maxCardNumber) {
        this.maxCardNumber = maxCardNumber;
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

    @Override
    public String toString() {
        return "CardGroup{" +
                "name='" + name + '\'' +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardGroup)) return false;
        CardGroup cardGroup = (CardGroup) o;
        return Objects.equals(getKey(), cardGroup.getKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSize()/*, getFlashCards()*/);
    }
}
