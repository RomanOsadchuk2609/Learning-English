package com.dariahaze.learning_english.ui.flashCards;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dariahaze.learning_english.model.CardGroup;
import com.dariahaze.learning_english.model.FlashCard;

import java.util.List;

public class  FlashCardsPagerAdapter  extends FragmentStatePagerAdapter {
    private static final float WIDTH_SCALE = 0.9f;
    private int numberOfTabs;
    private CardGroup cardGroup;
    private List<FlashCard> flashCards;
    private boolean isEditable = false;
    private int cardGroupSize;

    public List<FlashCard> getFlashCards() {
        return flashCards;
    }

    public void setFlashCards(List<FlashCard> flashCards) {
        this.flashCards = flashCards;
        numberOfTabs = flashCards.size();
        notifyDataSetChanged();
    }

    public void addCard(FlashCard flashCard){
        if (!flashCards.contains(flashCard)){
            flashCards.add(flashCard);
            numberOfTabs++;
            if (numberOfTabs > cardGroupSize){
                cardGroupSize = numberOfTabs;
            }
            notifyDataSetChanged();
        }
    }

    public FlashCardsPagerAdapter(FragmentManager fm, List<FlashCard> flashCards, int cardGroupSize, boolean isEditable) {
        super(fm);
        this.flashCards = flashCards;
        this.numberOfTabs = flashCards.size();
        this.isEditable = isEditable;
        this.cardGroupSize = cardGroupSize;
    }

    @Override
    public Fragment getItem(int index) {
        return CardFragment.newInstance(flashCards.get(index), index+1, cardGroupSize,isEditable);
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }


    public CardGroup getCardGroup() {
        return cardGroup;
    }
}
