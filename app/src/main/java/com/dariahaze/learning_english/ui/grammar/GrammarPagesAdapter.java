package com.dariahaze.learning_english.ui.grammar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class GrammarPagesAdapter extends FragmentStatePagerAdapter {
    private int numberOfTabs;

    public GrammarPagesAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0: return GrammarFragment.newInstance();
            case 1: return TensesFragment.newInstance();
            case 2: return GrammarOtherFragment.newInstance();
            default: return GrammarFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
