package com.dariahaze.learning_english.ui.flashCards;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dariahaze.learning_english.model.CardGroup;
import com.dariahaze.learning_english.ui.grammar.GrammarFragment;
import com.dariahaze.learning_english.ui.grammar.GrammarOtherFragment;
import com.dariahaze.learning_english.ui.grammar.TensesFragment;

public class  FlashCardsPagerAdapter  extends FragmentStatePagerAdapter {
    private static final float WIDTH_SCALE = 0.9f;
    private int numberOfTabs;
    private CardGroup cardGroup;

    public FlashCardsPagerAdapter(FragmentManager fm, CardGroup cardGroup) {
        super(fm);
        this.cardGroup = cardGroup;
        this.numberOfTabs = cardGroup.getSize();
    }

    @Override
    public Fragment getItem(int index) {
        return CardFragment.newInstance(cardGroup.getFlashCards().get(index), index+1, numberOfTabs);
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }


    /*@Override
    public float getPageWidth(int position) {
        return WIDTH_SCALE;
    }*/

    public CardGroup getCardGroup() {
        return cardGroup;
    }
}
