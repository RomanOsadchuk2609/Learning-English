package com.dariahaze.learning_english.model;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class CardGroup implements Serializable {
    private String name;
    private List<FlashCard> flashCards;

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
        return flashCards.size();
    }

    public List<FlashCard> getFlashCards() {
        return flashCards;
    }

    public void setFlashCards(List<FlashCard> flashCards) {
        this.flashCards = flashCards;
    }
}
