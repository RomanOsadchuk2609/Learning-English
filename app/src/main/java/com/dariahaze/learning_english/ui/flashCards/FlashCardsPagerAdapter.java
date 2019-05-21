package com.dariahaze.learning_english.ui.flashCards;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.dariahaze.learning_english.model.CardGroup;
import com.dariahaze.learning_english.model.FlashCard;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class  FlashCardsPagerAdapter  extends FragmentStatePagerAdapter {
    private static final float WIDTH_SCALE = 0.9f;
    private int numberOfTabs;
    private CardGroup cardGroup;
    private List<FlashCard> flashCards;
    private boolean isEditable = false;
    private int cardGroupSize;

    private DatabaseReference mCardGroupReference;

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
        else if (flashCards.contains(flashCard)){
            FlashCard oldFlashCard = flashCards.get(flashCards.indexOf(flashCard));
            oldFlashCard = flashCard;
            notifyDataSetChanged();
        }
    }

    public void deleteCard(FlashCard flashCard){
        if (flashCards.contains(flashCard)){
            flashCards.remove(flashCard);
            numberOfTabs--;
            cardGroupSize--;
            cardGroup.setSize(cardGroupSize);
            mCardGroupReference.setValue(cardGroup);
            notifyDataSetChanged();;

        }
    }

    public FlashCardsPagerAdapter(FragmentManager fm, List<FlashCard> flashCards, CardGroup cardGroup,
                                  boolean isEditable) {
        super(fm);
        this.flashCards = flashCards;
        this.numberOfTabs = flashCards.size();
        this.isEditable = isEditable;
        this.cardGroup = cardGroup;
        this.cardGroupSize = cardGroup.getSize();
        mCardGroupReference = FirebaseDatabase.getInstance().getReference(cardGroup.getPath());
    }

    @Override
    public Fragment getItem(int index) {
        return CardFragment.newInstance(flashCards.get(index), index+1, cardGroupSize,
                isEditable, this);
    }



    @Override
    public int getCount() {
        return numberOfTabs;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }
}
