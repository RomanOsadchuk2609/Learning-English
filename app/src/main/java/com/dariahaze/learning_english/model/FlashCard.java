package com.dariahaze.learning_english.model;

import java.io.Serializable;

public class FlashCard implements Serializable {
    private String frontText;
    private String backText;

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
}
