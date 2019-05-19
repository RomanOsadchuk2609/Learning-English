package com.dariahaze.learning_english.model;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CardGroup implements Serializable {
    private String name;
    private int size;
    private List<FlashCard> flashCards;

    public CardGroup() {
        flashCards = new ArrayList<>();
    }

    public CardGroup(String name, List<FlashCard> flashCards) {
        this.name = name;
        this.flashCards = flashCards;
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

    public List<FlashCard> getFlashCards() {
        return flashCards;
    }

    public void setFlashCards(List<FlashCard> flashCards) {
        this.flashCards = flashCards;
    }

    @Override
    public String toString() {
        return "CardGroup{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", flashCards=" + flashCards +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardGroup)) return false;
        CardGroup cardGroup = (CardGroup) o;
        return getSize() == cardGroup.getSize() &&
                Objects.equals(getName(), cardGroup.getName()) /*&&
                Objects.equals(getFlashCards(), cardGroup.getFlashCards())*/;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSize()/*, getFlashCards()*/);
    }
}
