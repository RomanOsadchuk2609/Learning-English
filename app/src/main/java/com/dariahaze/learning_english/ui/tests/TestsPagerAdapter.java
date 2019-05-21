package com.dariahaze.learning_english.ui.tests;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dariahaze.learning_english.ui.grammar.GrammarFragment;
import com.dariahaze.learning_english.ui.grammar.GrammarOtherFragment;
import com.dariahaze.learning_english.ui.grammar.TensesFragment;

public class TestsPagerAdapter extends FragmentStatePagerAdapter {
    private int numberOfTabs = 2;

    public TestsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0: return PracticeFragment.newInstance();
            case 1: return RandomTestFragment.newInstance();
            default: return PracticeFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
